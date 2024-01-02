package controller.tabControllers;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import model.ScanModel;
import model.utilities.DebounceDocListener;
import view.tabs.ScanTab;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class ScanTabController {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public ScanTabController(ScanModel scanModel, ScanTab scanTabView) {
    _scanModel   = scanModel;
    _scanTabView = scanTabView;
    _addDownloaderListeners();
    _addActionPanelListeners();
    _syncView2Model();
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final int DEBOUNCE_DELAY = 300;
  private final ScanTab   _scanTabView;
  private final ScanModel _scanModel;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private void _addActionPanelListeners() {
    _scanTabView.addSendDownloadReqListener(e->new SendReDownloadRequestWorker().execute());
    _scanTabView.addSendPreflightReqListener(e->new SendPreflightRequestWorker().execute());
  }

  private void _addDownloaderListeners() {
    _scanTabView.addPreflightEndpointListener(new PreflightEndpointListener());
    _scanTabView.addReplaceBackslashListener(
        e->_scanModel.setReplaceBackslash(_scanTabView.replaceBackslash()));
    _scanTabView.addStartMarkerListener(new AddStartMarkerListener());
    _scanTabView.addEndMarkerListener(new AddEndMarkerListener());
    _scanTabView.addPrefixListener(e->_scanModel.setPrefix(_scanTabView.getPrefix()));
    _scanTabView.addSuffixListener(e->_scanModel.setSuffix(_scanTabView.getSuffix()));

    _scanTabView.addStaticUrlListener(e->{
      //todo: there are two functions that work on validating static urls,
      //      can these be consolidated?
      String input = _scanTabView.getStaticUrl();
      if (_scanModel.setStaticUrl(input)) {
        _scanTabView.setReDownloadEditor(_scanModel.getReDownloadRequest());
        _scanTabView.setStaticUrlBackground(Color.white);
      }
      else {
        _scanTabView.setReDownloadEditor(HttpRequest.httpRequest());
        _scanTabView.setStaticUrlBackground(Color.red);
      }
    });
  }

  private void _syncView2Model(){
    _scanTabView.baseConfigTemplate().setReplaceFileName(_scanModel.baseConfigModel().replaceFileName());
    _scanTabView.baseConfigTemplate().setReplaceFileSize(_scanModel.baseConfigModel().replaceFileSize());
    _scanTabView.baseConfigTemplate().setReplaceContentType(_scanModel.baseConfigModel().replaceContentType());
    _scanTabView.baseConfigTemplate().setAddToLoggingChkBox(_scanModel.baseConfigModel().addToLoggingTab());
    _scanTabView.baseConfigTemplate().setWgetCurlPayloads(_scanModel.baseConfigModel().wgetCurlPayloads());
    _scanTabView.baseConfigTemplate().setSleepTime(_scanModel.baseConfigModel().sleepTime());
    _scanTabView.baseConfigTemplate().setThrottleValue(_scanModel.baseConfigModel().throttleTime());

    _scanTabView.baseConfigTemplate().setGifFileType(_scanModel.baseConfigModel().gifFileType());
    _scanTabView.baseConfigTemplate().setPngFileType(_scanModel.baseConfigModel().pngFileType());
    _scanTabView.baseConfigTemplate().setJpegFileType(_scanModel.baseConfigModel().jpegFileType());
    _scanTabView.baseConfigTemplate().setTiffFileType(_scanModel.baseConfigModel().tiffFileType());
    _scanTabView.baseConfigTemplate().setIcoFileType(_scanModel.baseConfigModel().icoFileType());
    _scanTabView.baseConfigTemplate().setSvgFileType(_scanModel.baseConfigModel().svgFileType());
    _scanTabView.baseConfigTemplate().setMvgFileType(_scanModel.baseConfigModel().mvgFileType());
    _scanTabView.baseConfigTemplate().setPdfFileType(_scanModel.baseConfigModel().pdfFileType());
    _scanTabView.baseConfigTemplate().setMp4FileType(_scanModel.baseConfigModel().mp4FileType());
    _scanTabView.baseConfigTemplate().setDocxFileType(_scanModel.baseConfigModel().docxFileType());
    _scanTabView.baseConfigTemplate().setXlsxFileType(_scanModel.baseConfigModel().xlsxFileType());
    _scanTabView.baseConfigTemplate().setSwfFileType(_scanModel.baseConfigModel().swfFileType());
    _scanTabView.baseConfigTemplate().setCsvFileType(_scanModel.baseConfigModel().csvFileType());
    _scanTabView.baseConfigTemplate().setZipFileType(_scanModel.baseConfigModel().zipFileType());
    _scanTabView.baseConfigTemplate().setGzipFileType(_scanModel.baseConfigModel().gzipFileType());
    _scanTabView.baseConfigTemplate().setHtmlFileType(_scanModel.baseConfigModel().htmlFileType());
    _scanTabView.baseConfigTemplate().setXmlFileType(_scanModel.baseConfigModel().xmlFileType());

    _scanTabView.baseConfigTemplate().setActivescanScanCheck(_scanModel.baseConfigModel().activescanScanCheck());
    _scanTabView.baseConfigTemplate().setImagetragickScanCheck(_scanModel.baseConfigModel().imagetragickScanCheck());
    _scanTabView.baseConfigTemplate().setMagickScanCheck(_scanModel.baseConfigModel().magickScanCheck());
    _scanTabView.baseConfigTemplate().setGsScanCheck(_scanModel.baseConfigModel().gsScanCheck());
    _scanTabView.baseConfigTemplate().setLibavformatScanCheck(_scanModel.baseConfigModel().libavformatScanCheck());
    _scanTabView.baseConfigTemplate().setPhpScanCheck(_scanModel.baseConfigModel().phpScanCheck());
    _scanTabView.baseConfigTemplate().setJspScanCheck(_scanModel.baseConfigModel().jspScanCheck());
    _scanTabView.baseConfigTemplate().setAspScanCheck(_scanModel.baseConfigModel().aspScanCheck());
    _scanTabView.baseConfigTemplate().setHtaccessScanCheck(_scanModel.baseConfigModel().htaccessScanCheck());
    _scanTabView.baseConfigTemplate().setCgiScanCheck(_scanModel.baseConfigModel().cgiScanCheck());
    _scanTabView.baseConfigTemplate().setSsiScanCheck(_scanModel.baseConfigModel().ssiScanCheck());
    _scanTabView.baseConfigTemplate().setXxeScanCheck(_scanModel.baseConfigModel().xxeScanCheck());
    _scanTabView.baseConfigTemplate().setXssScanCheck(_scanModel.baseConfigModel().xssScanCheck());
    _scanTabView.baseConfigTemplate().setEicarScanCheck(_scanModel.baseConfigModel().eicarScanCheck());
    _scanTabView.baseConfigTemplate().setPdfInjectionScanCheck(_scanModel.baseConfigModel().pdfInjectionScanCheck());
    _scanTabView.baseConfigTemplate().setSsrfScanCheck(_scanModel.baseConfigModel().ssrfScanCheck());
    _scanTabView.baseConfigTemplate().setCsvInjectionScanCheck(_scanModel.baseConfigModel().csvInjectionScanCheck());
    _scanTabView.baseConfigTemplate().setPathTraversalScanCheck(_scanModel.baseConfigModel().pathTraversalScanCheck());
    _scanTabView.baseConfigTemplate().setPolyglotScanCheck(_scanModel.baseConfigModel().polyglotScanCheck());
    _scanTabView.baseConfigTemplate().setFingerpingScanCheck(_scanModel.baseConfigModel().fingerpingScanCheck());
    _scanTabView.baseConfigTemplate().setQuirksScanCheck(_scanModel.baseConfigModel().quirksScanCheck());
    _scanTabView.baseConfigTemplate().setUrlReplacerScanCheck(_scanModel.baseConfigModel().urlReplacerScanCheck());
    _scanTabView.baseConfigTemplate().setRecursiveUploaderScanCheck(_scanModel.baseConfigModel().recursiveUploaderScanCheck());
    _scanTabView.baseConfigTemplate().setFuzzerScanCheck(_scanModel.baseConfigModel().fuzzerScanCheck());
    _scanTabView.baseConfigTemplate().setDosScanCheck(_scanModel.baseConfigModel().dosScanCheck());
  }

  private void displayMessage(String message){
      _scanTabView.displayMessage(message);
  }

  ////////////////////////////////////////
  // PRIVATE CLASSES
  ////////////////////////////////////////
  private class SendPreflightRequestWorker extends SwingWorker<HttpRequestResponse, Void> {
    @Override
    protected HttpRequestResponse doInBackground() {
      // Perform the HTTP request in a background thread
      return _scanModel.sendPreflightReq();
    }

    @Override
    protected void done() {
      // This method is executed in the EDT after the background task is completed
      HttpRequestResponse requestResponse = null; // get the result of doInBackground
      try {
        requestResponse = get();
      }
      catch (InterruptedException | ExecutionException e) {
        String message =
            "Something went wrong when requesting \"" + _scanModel.getPreflightRequest().url() +
            "\"\n" +
            e.getMessage() + "\n" + Arrays.toString(e.getStackTrace());
        displayMessage(message);
      }
      _scanTabView.updatePreflightWindows(requestResponse);
    }
  }

  private class SendReDownloadRequestWorker extends SwingWorker<HttpRequestResponse, Void> {
    @Override
    protected HttpRequestResponse doInBackground() {
      // Perform the HTTP request in a background thread
      return _scanModel.sendReDownloadReq();
    }

    @Override
    protected void done() {
      // This method is executed in the EDT after the background task is completed
      HttpRequestResponse requestResponse = null; // get the result of doInBackground
      try {
        requestResponse = get();
      }
      catch (InterruptedException | ExecutionException e) {
        String message =
            "Something went wrong when requesting \"" + _scanModel.getPreflightRequest().url() +
            "\"\n" +
            e.getMessage() + "\n" + Arrays.toString(e.getStackTrace());
        displayMessage(message);
      }
      _scanTabView.updateReDownloadWindows(requestResponse);
    }
  }

  private class PreflightEndpointListener extends DebounceDocListener {
    public PreflightEndpointListener() {
      super(DEBOUNCE_DELAY, e->{
        String input = _scanTabView.preflightEndpoint();
        if (
            _scanModel.getPreflightRequest() == null ||
            !_scanModel.getPreflightRequest().url().equals(input)
        ) {
          _scanModel.setPreflightEndpointInput(input);
          _scanTabView.updatePreflightWindow(_scanModel.getPreflightRequest());
          _scanTabView.setPreflightEndpointBackground(Color.white);
        }
        else
          _scanTabView.setPreflightEndpointBackground(Color.red);
      });
    }
  }

  private class AddStartMarkerListener extends DebounceDocListener {
    public AddStartMarkerListener() {
      super(DEBOUNCE_DELAY, e->{
        String input = _scanTabView.getStartMarker();
        String match = _scanModel.setStartMarker(input);
        if (match.isEmpty()) {
          _scanTabView.setStartMarkerBackground(Color.red);
          _scanTabView.setReDownloadEditor(HttpRequest.httpRequest());
        }
        else {
          _scanTabView.setStartMarkerBackground(Color.gray);
          _scanTabView.setReDownloadSelection(match);
          _scanTabView.setReDownloadEditor(_scanModel.getReDownloadRequest());
        }
      });
    }
  }

  private class AddEndMarkerListener extends DebounceDocListener {
    public AddEndMarkerListener() {
      super(DEBOUNCE_DELAY, e->{
        String input = _scanTabView.getEndMarker();
        String match = _scanModel.setEndMarker(input);
        if (match.isEmpty()) {
          _scanTabView.setEndMarkerBackground(Color.red);
          _scanTabView.setReDownloadEditor(HttpRequest.httpRequest());
        }
        else {
          _scanTabView.setEndMarkerBackground(Color.gray);
          _scanTabView.setReDownloadSelection(match);
          _scanTabView.setReDownloadEditor(_scanModel.getReDownloadRequest());
        }
      });
    }
  }
}
