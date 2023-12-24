package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

public class ScanModel {
  public ScanModel(MontoyaApi api, BaseConfigModel baseConfigModel, HttpRequestResponse requestResponse) {
    _api             = api;
    _baseConfigModel = baseConfigModel.clone();
    _requestResponse = requestResponse;
  }
  
  // PUBLIC METHODS //
  public BaseConfigModel baseConfigModel() {
    return _baseConfigModel;
  }
  
  public HttpRequestResponse sendPreflightReq() {
    return _api.http().sendRequest(_preflightRequest);
  }
  
  // ReDownloader Functions
  public void setStartMarker(String s)               {_startMarker = s;}
  public void setEndMarker(String s)                 {_endMarker = s;}
  public void setReplaceBackslash(boolean b)         {_replaceBackslash = b;}
  public void setPrefix(String s)                    {_prefix = s;}
  public void setSuffix(String s)                    {_suffix = s;}
  public void setStaticUrl(String s)                 {_staticUrl = s;}
  public void setPreflightEndpointInput(String s)    {
    try                 { _preflightRequest = HttpRequest.httpRequestFromUrl(s);}
    catch (Exception ignore) {}
  }
  
  public HttpRequest getPreflightRequest()           {return _preflightRequest;}
  
  // Base Config Variables
  private final MontoyaApi          _api;
  private final BaseConfigModel     _baseConfigModel;
  private final HttpRequestResponse _requestResponse;
  
  // Preflight
  private HttpRequest _preflightRequest;
  
  // ReDownloader Variables
  private String  _startMarker;
  private String  _endMarker;
  private boolean _replaceBackslash;
  private String  _prefix;
  private String  _suffix;
  private String  _staticUrl;
}

