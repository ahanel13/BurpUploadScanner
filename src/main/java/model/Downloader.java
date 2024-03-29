package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import model.utilities.RequestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {
  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public Downloader(MontoyaApi api, HttpRequestResponse uploadRequestResponse) {
    _api                   = api;
    _uploadRequestResponse = uploadRequestResponse;
  }

  ////////////////////////////////////////
  // PUBLIC FIELDS
  ////////////////////////////////////////
  public void setReplaceBackslash(boolean b)        {_replaceBackslash = b;}
  public void setPrefix(String s)                   {_prefix = s;}
  public void setSuffix(String s)                   {_suffix = s;}
  public HttpRequest getPreflightRequest()          {return _preflightRequest;}
  public HttpRequest getReDownloadRequest()         {return _reDownloadRequest;}
  public void setPreflightEndpointInput(String url) {_preflightRequest = HttpRequest.httpRequestFromUrl(url);}

  public HttpRequestResponse sendPreflightReq() {
    HttpRequestResponse requestResponse = _api.http().sendRequest(_preflightRequest);
    _preflightResponse = requestResponse.response();
    return requestResponse;
  }

  public HttpRequestResponse sendReDownloadReq() {
    HttpRequestResponse requestResponse = _api.http().sendRequest(_reDownloadRequest);
    _reDownloadResponse = requestResponse.response();
    return requestResponse;
  }

  public boolean setStaticUrl(String url) {
    _staticUrl = url;
    if (RequestUtils.isValidURL(url)) {
      _reDownloadRequest = HttpRequest.httpRequestFromUrl(url);
      return true;
    }
    else return false;
  }

  public String setStartMarker(String s) {
    _startMarker = s;
    return setDownloaderMarkerSelections();
  }

  public String setEndMarker(String s) {
    _endMarker = s;
    return setDownloaderMarkerSelections();
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final MontoyaApi          _api;
  private final HttpRequestResponse _uploadRequestResponse;
  private       String              _startMarker       = "";
  private       String              _endMarker         = "";
  private       boolean             _replaceBackslash;
  private       String              _prefix;
  private       String              _suffix;
  private       String              _staticUrl;
  private       HttpRequest         _preflightRequest;
  private       HttpResponse        _preflightResponse;
  private       HttpRequest         _reDownloadRequest = HttpRequest.httpRequest();
  private       HttpResponse        _reDownloadResponse;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private String setDownloaderMarkerSelections() {
    StringBuilder selection = new StringBuilder();
    HttpResponse response = (preflightUsed()
        ? _preflightResponse : _uploadRequestResponse.response());

    if (!_startMarker.isEmpty() && !_endMarker.isEmpty())
      selection.append(_startMarker).append(".*").append(_endMarker);
    else if (!_startMarker.isEmpty()) selection.append(_startMarker);
    else if (!_endMarker.isEmpty()) selection.append(_endMarker);

    Pattern pattern = Pattern.compile(selection.toString());
    Matcher matcher = pattern.matcher(response.toString());

    if (matcher.find()) {
      String match = matcher.group(0); // This will contain the matched string
      if (!_endMarker.isEmpty()) {
        match = match.substring(_startMarker.length());
        match = match.substring(0, match.length() - _endMarker.length());
        if (!setReDownloadRequest(match)) {
          return "";
        }
      }
      return match;
    }
    return "";
  }

  private boolean preflightUsed() {return _preflightRequest != null && !_preflightRequest.toString().isEmpty();}

  private boolean setReDownloadRequest(String match) {
    try {
      _reDownloadRequest = HttpRequest.httpRequestFromUrl(match);
      for (HttpHeader header : _uploadRequestResponse.request().headers()) {
        if (
            !header.name().equalsIgnoreCase("host") ||
            !header.name().equalsIgnoreCase("content-type"))
          _reDownloadRequest = _reDownloadRequest.withHeader(header.name(), header.value());
      }
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }
}
