package controller.tabControllers;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import model.scan.ScanCheckWorker;
import model.scan.ScanLog;
import model.scan.ScanModel;
import model.utilities.DebounceDocListener;
import model.utilities.RequestUtils;
import view.tabs.ScanTab;

import java.awt.*;
import java.util.concurrent.ExecutionException;

////////////////////////////////////////
// CLASS ScanTabController
////////////////////////////////////////
public class ScanTabController {

////////////////////////////////////////
// PUBLIC FUNCTIONS
////////////////////////////////////////
//-----------------------------------------------------------------------------
public ScanTabController(ScanModel scanModel, ScanTab scanTabView) {
  _scanModel   = scanModel;
  _scanTabView = scanTabView;
  _scanLog     = new ScanLog();
  _scanWorker  = new ScanCheckWorker(_scanModel, _scanLog);
  _addDownloaderListeners();
  _addActionPanelListeners();
  _syncView2Model();
}


////////////////////////////////////////
// PRIVATE FIELDS
////////////////////////////////////////
private static final int DEBOUNCE_DELAY = 300;

private final ScanTab         _scanTabView;
private final ScanModel       _scanModel;
private final ScanLog         _scanLog;
private       ScanCheckWorker _scanWorker;


////////////////////////////////////////
// PRIVATE METHODS
////////////////////////////////////////
//-----------------------------------------------------------------------------
private void _addActionPanelListeners() {
  _scanTabView.addSendDownloadReqListener(e-> {
    try {
      HttpRequestResponse requestResponse = _scanModel.sendReDownloadReq();
      _scanTabView.updateReDownloadWindows(requestResponse);
    }
    catch (ExecutionException | InterruptedException ew) {
      displayMessage("Exception occurred when sending redownload request:\n" + ew);
    }
  });

  _scanTabView.addSendPreflightReqListener(e-> {
    try {
      HttpRequestResponse requestResponse = _scanModel.sendPreflightReq();
      _scanTabView.updatePreflightWindows(requestResponse);
    }
    catch (ExecutionException | InterruptedException ew) {
      displayMessage("Exception occurred when sending redownload request:\n" + ew);
    }
  });

  _scanTabView.addStartScanListener(e->{
    _scanTabView.enableStopBtn();
    _scanTabView.disableStartBtn();

    // if scan is being resumed
    if(_scanWorker.isCancelled())
      _scanWorker = new ScanCheckWorker(_scanWorker, _scanModel);

    _scanWorker.execute();
  });

  //todo: implement a restart scan

  _scanTabView.addStopScanListener(e->{
    _scanTabView.disableStopBtn();
    _scanTabView.enableStartBtn();
    _scanWorker.cancel(false);
  });
}

//-----------------------------------------------------------------------------
private void _addDownloaderListeners() {
  _scanTabView.addPreflightEndpointListener(new PreflightEndpointListener());
  _scanTabView.addReplaceBackslashListener(
      e->_scanModel.setReplaceBackslash(_scanTabView.replaceBackslash()));
  _scanTabView.addStartMarkerListener(new AddStartMarkerListener());
  _scanTabView.addEndMarkerListener(new AddEndMarkerListener());
  _scanTabView.addPrefixListener(e->_scanModel.setPrefix(_scanTabView.getPrefix()));
  _scanTabView.addSuffixListener(e->_scanModel.setSuffix(_scanTabView.getSuffix()));

  _scanTabView.addStaticUrlListener(new StaticUrlListener());
}

//-----------------------------------------------------------------------------
private void _syncView2Model(){
  _scanTabView.baseConfigTemplate().setReplaceFileName(_scanModel.replaceFileName());
  _scanTabView.baseConfigTemplate().setReplaceFileSize(_scanModel.replaceFileSize());
  _scanTabView.baseConfigTemplate().setReplaceContentType(_scanModel.replaceContentType());
  _scanTabView.baseConfigTemplate().setAddToLoggingChkBox(_scanModel.addToLoggingTab());
  _scanTabView.baseConfigTemplate().setWgetCurlPayloads(_scanModel.wgetCurlPayloads());
  _scanTabView.baseConfigTemplate().setSleepTime(_scanModel.sleepTime());
  _scanTabView.baseConfigTemplate().setThrottleValue(_scanModel.throttleTime());

  _scanTabView.baseConfigTemplate().setGifFileType(_scanModel.gifFileType());
  _scanTabView.baseConfigTemplate().setPngFileType(_scanModel.pngFileType());
  _scanTabView.baseConfigTemplate().setJpegFileType(_scanModel.jpegFileType());
  _scanTabView.baseConfigTemplate().setTiffFileType(_scanModel.tiffFileType());
  _scanTabView.baseConfigTemplate().setIcoFileType(_scanModel.icoFileType());
  _scanTabView.baseConfigTemplate().setSvgFileType(_scanModel.svgFileType());
  _scanTabView.baseConfigTemplate().setMvgFileType(_scanModel.mvgFileType());
  _scanTabView.baseConfigTemplate().setPdfFileType(_scanModel.pdfFileType());
  _scanTabView.baseConfigTemplate().setMp4FileType(_scanModel.mp4FileType());
  _scanTabView.baseConfigTemplate().setDocxFileType(_scanModel.docxFileType());
  _scanTabView.baseConfigTemplate().setXlsxFileType(_scanModel.xlsxFileType());
  _scanTabView.baseConfigTemplate().setSwfFileType(_scanModel.swfFileType());
  _scanTabView.baseConfigTemplate().setCsvFileType(_scanModel.csvFileType());
  _scanTabView.baseConfigTemplate().setZipFileType(_scanModel.zipFileType());
  _scanTabView.baseConfigTemplate().setGzipFileType(_scanModel.gzipFileType());
  _scanTabView.baseConfigTemplate().setHtmlFileType(_scanModel.htmlFileType());
  _scanTabView.baseConfigTemplate().setXmlFileType(_scanModel.xmlFileType());

  _scanTabView.baseConfigTemplate().setActivescanScanCheck(_scanModel.activescanScanCheck());
  _scanTabView.baseConfigTemplate().setImagetragickScanCheck(_scanModel.imagetragickScanCheck());
  _scanTabView.baseConfigTemplate().setMagickScanCheck(_scanModel.magickScanCheck());
  _scanTabView.baseConfigTemplate().setGsScanCheck(_scanModel.gsScanCheck());
  _scanTabView.baseConfigTemplate().setLibavformatScanCheck(_scanModel.libavformatScanCheck());
  _scanTabView.baseConfigTemplate().setPhpScanCheck(_scanModel.phpScanCheck());
  _scanTabView.baseConfigTemplate().setJspScanCheck(_scanModel.jspScanCheck());
  _scanTabView.baseConfigTemplate().setAspScanCheck(_scanModel.aspScanCheck());
  _scanTabView.baseConfigTemplate().setHtaccessScanCheck(_scanModel.htaccessScanCheck());
  _scanTabView.baseConfigTemplate().setCgiScanCheck(_scanModel.cgiScanCheck());
  _scanTabView.baseConfigTemplate().setSsiScanCheck(_scanModel.ssiScanCheck());
  _scanTabView.baseConfigTemplate().setXxeScanCheck(_scanModel.xxeScanCheck());
  _scanTabView.baseConfigTemplate().setXssScanCheck(_scanModel.xssScanCheck());
  _scanTabView.baseConfigTemplate().setEicarScanCheck(_scanModel.eicarScanCheck());
  _scanTabView.baseConfigTemplate().setPdfInjectionScanCheck(_scanModel.pdfInjectionScanCheck());
  _scanTabView.baseConfigTemplate().setSsrfScanCheck(_scanModel.ssrfScanCheck());
  _scanTabView.baseConfigTemplate().setCsvInjectionScanCheck(_scanModel.csvInjectionScanCheck());
  _scanTabView.baseConfigTemplate().setPathTraversalScanCheck(_scanModel.pathTraversalScanCheck());
  _scanTabView.baseConfigTemplate().setPolyglotScanCheck(_scanModel.polyglotScanCheck());
  _scanTabView.baseConfigTemplate().setFingerpingScanCheck(_scanModel.fingerpingScanCheck());
  _scanTabView.baseConfigTemplate().setQuirksScanCheck(_scanModel.quirksScanCheck());
  _scanTabView.baseConfigTemplate().setUrlReplacerScanCheck(_scanModel.urlReplacerScanCheck());
  _scanTabView.baseConfigTemplate().setRecursiveUploaderScanCheck(_scanModel.recursiveUploaderScanCheck());
  _scanTabView.baseConfigTemplate().setFuzzerScanCheck(_scanModel.fuzzerScanCheck());
  _scanTabView.baseConfigTemplate().setDosScanCheck(_scanModel.dosScanCheck());
}

//-----------------------------------------------------------------------------
private void displayMessage(String message){
    _scanTabView.displayMessage(message);
}


////////////////////////////////////////
// PRIVATE CLASSES
////////////////////////////////////////
//-----------------------------------------------------------------------------
private class PreflightEndpointListener extends DebounceDocListener {
  public PreflightEndpointListener() {
    super(DEBOUNCE_DELAY, e->{

      String input = _scanTabView.preflightEndpoint();

      // if user no longer wants to user preflight option
      if (input.trim().isEmpty()) {
        _scanModel.setPreflightEndpointInput("");
        _scanTabView.setPreflightEndpointBackground(Color.gray);
        _scanTabView.disablePreflightWindows();
        _scanTabView.triggerParsers();
      }
      // else user is trying to pass a URL
      else {
        // if user has not set a URL or is a different url
        if (
            _scanModel.getPreflightRequest() == null ||
            !_scanModel.getPreflightRequest().url().equals(input)
        ) {
          if (RequestUtils.isValidURL(input)) {
            _scanModel.setPreflightEndpointInput(input);
            _scanTabView.updatePreflightWindow(_scanModel.getPreflightRequest());
            _scanTabView.setPreflightEndpointBackground(new Color(0, 144, 18, 131));
          }
          else {
            _scanTabView.setPreflightEndpointBackground(new Color(144, 0, 0, 100));
            _scanTabView.disablePreflightWindows();
          }
        }
      }
    });
  }
}

//-----------------------------------------------------------------------------
private class StaticUrlListener extends DebounceDocListener{
  public StaticUrlListener() {
    super(DEBOUNCE_DELAY, e-> {
        //todo: there are two functions that work on validating static urls,
        //      can these be consolidated?
        String input = _scanTabView.getStaticUrl();

        // if input is a valid url
        if (RequestUtils.isValidURL(input)) {
          _scanModel.setStaticUrl(input);
          _scanTabView.setReDownloadEditor(_scanModel.getReDownloadRequest());
          _scanTabView.setStaticUrlBackground(Color.white);
        }
        else {
          _scanTabView.setReDownloadEditor(HttpRequest.httpRequest());
          _scanTabView.setStaticUrlBackground(Color.red);
        }
    });
  }
}

//-----------------------------------------------------------------------------
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

//-----------------------------------------------------------------------------
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
////////////////////////////////////////
// END CLASS ScanTabController
////////////////////////////////////////