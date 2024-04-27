package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import model.checks.PhpChecks;
import model.checks.UploaderCheck;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ScanCheckWorker extends SwingWorker<Void, Integer> {

  //---------------------------------------------------------------------------
  public ScanCheckWorker(ScanModel model) {
    _scanModel             = model;
    _baseConfigModel       = model.baseConfigModel();
    _uploadRequestResponse = model.getUploadReqResp();
    _api                   = model.getApi();
    _downloader            = model.getDownloader();
    _checks                = new ArrayList<>();
  }

  //---------------------------------------------------------------------------
  // SwingWorkers cannot be restarted, a new worker is required. This is a way
  // to manage/pass on the state of the previous worker.
  public ScanCheckWorker(ScanCheckWorker cancelledWorker){
    _scanModel             = cancelledWorker._scanModel;
    _baseConfigModel       = cancelledWorker._baseConfigModel;
    _uploadRequestResponse = cancelledWorker._uploadRequestResponse;
    _api                   = cancelledWorker._api;
    _downloader            = cancelledWorker._downloader;
    _completedChecks       = cancelledWorker._completedChecks;
    _totalChecks           = cancelledWorker._totalChecks;
    _checks                = cancelledWorker._checks;
  }

  private void addChecks() {
    // if a scan was cancelled
    if(_totalChecks != _completedChecks)
      return;

    _checks.clear();
    setProgress(0);

    //if PHP Checks are enabled
    if (_baseConfigModel.phpScanCheck()){
      PhpChecks check1 = new PhpChecks(_uploadRequestResponse, _api, _downloader);
      _checks.add(check1);
      _totalChecks += check1.getTotalChecks();
    }
  }

  //---------------------------------------------------------------------------
  @Override
  protected Void doInBackground() {
    addChecks();

    try {
      for (UploaderCheck check : _checks) {
        if (isCancelled())
          return null; // stop scanning

        check.start();

        // Keep looping until the thread is interrupted or finishes
        while (check.isAlive()) {
          Thread.sleep(1000); // Sleep briefly between checks
          if (isCancelled()) {
            // If cancellation is requested, interrupt the thread and exit the loop
            check.interrupt();
          }
        }

        publish(check.getTotalChecks());
      }
    } catch (InterruptedException e) {
      _api.logging().logToError("Something went wrong...", e);
    }

    return null;
  }

  //---------------------------------------------------------------------------
  @Override
  protected void process(List<Integer> chunks) {
    for(Integer i : chunks){
      _completedChecks += i;
      setProgress((_totalChecks/_completedChecks) * 100);
    }
  }

  //---------------------------------------------------------------------------
  @Override
  protected void done() {
    super.done();
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final List<UploaderCheck> _checks;
  private final ScanModel           _scanModel;
  private final HttpRequestResponse _uploadRequestResponse;
  private final Downloader          _downloader;
  private final MontoyaApi          _api;
  private final BaseConfigModel     _baseConfigModel;

  private int     _totalChecks     = 0;
  private int     _completedChecks = 0;
}
