package model.scan;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

import java.util.concurrent.ExecutionException;

public class ScanModel extends BaseConfigModel {
  public ScanModel(MontoyaApi api, BaseConfigModel baseConfigModel, HttpRequestResponse uploadRequestResponse) {
    super(baseConfigModel);
    _api                   = api;
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

  public HttpRequest getPreflightRequest()      {return _downloader.getPreflightRequest();}
  public HttpRequest getReDownloadRequest()     {return _downloader.getReDownloadRequest();}
  public HttpRequestResponse getUploadReqResp() {return _uploadRequestResponse;}

  public Downloader getDownloader()              {return _downloader;}
  public MontoyaApi getApi()                     {return _api;}

  public HttpRequestResponse sendPreflightReq()
  throws InterruptedException, ExecutionException {return _downloader.sendPreflightReq();}

  public HttpRequestResponse sendReDownloadReq()
  throws InterruptedException, ExecutionException {return _downloader.sendReDownloadReq();}


  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final MontoyaApi          _api;
  private final HttpRequestResponse _uploadRequestResponse;
  private final Downloader          _downloader;
}
