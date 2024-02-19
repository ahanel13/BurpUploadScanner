package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.scanner.audit.issues.AuditIssue;
import model.checks.PhpChecks;
import model.utilities.MultipartRequestFactory;

import java.util.ArrayList;
import java.util.List;

public class ScanModel {
  public ScanModel(MontoyaApi api, BaseConfigModel baseConfigModel, HttpRequestResponse uploadRequestResponse) {
    _api                   = api;
    _baseConfigModel       = baseConfigModel.clone();
    _uploadRequestResponse = uploadRequestResponse;
    _downloader            = new Downloader(_api, _uploadRequestResponse);
  }

  ////////////////////////////////////////
  // PUBLIC METHODS //
  ////////////////////////////////////////
  public void setReplaceBackslash(boolean b)        {_downloader.setReplaceBackslash(b);}
  public void setPrefix(String s)                   {_downloader.setPrefix(s);}
  public void setSuffix(String s)                   {_downloader.setSuffix(s);}
  public void setPreflightEndpointInput(String url) {_downloader.setPreflightEndpointInput(url);}
  public void setStaticUrl(String url)              {_downloader.setStaticUrl(url);}
  public String setStartMarker(String s)            {return _downloader.setStartMarker(s);}
  public String setEndMarker(String s)              {return _downloader.setEndMarker(s);}

  public HttpRequest getPreflightRequest()  {return _downloader.getPreflightRequest();}
  public HttpRequest getReDownloadRequest() {return _downloader.getReDownloadRequest();}

  public BaseConfigModel baseConfigModel()       {return _baseConfigModel;}
  public HttpRequestResponse sendPreflightReq()  {return _downloader.sendPreflightReq();}
  public HttpRequestResponse sendReDownloadReq() {return _downloader.sendReDownloadReq();}

  public void startScan() {
    List<AuditIssue>        auditIssues = new ArrayList<>();
    MultipartRequestFactory reqFactory  = new MultipartRequestFactory(_uploadRequestResponse.request());

    if (_baseConfigModel.phpScanCheck()) {
      PhpChecks        phpChecks = new PhpChecks(_api, reqFactory, _downloader);
      List<AuditIssue> issues     = phpChecks.basicRceCheck();

      if (!issues.isEmpty())
        auditIssues.addAll(issues);
    }

    for (AuditIssue issue : auditIssues) {
      _api.siteMap().add(issue);
    }
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final MontoyaApi          _api;
  private final BaseConfigModel     _baseConfigModel;
  private final HttpRequestResponse _uploadRequestResponse;
  private final Downloader          _downloader;
}
