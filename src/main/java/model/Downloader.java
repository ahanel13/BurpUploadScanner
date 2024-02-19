package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpHeader;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.params.ParsedHttpParameter;
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
    _api      = api;
    _filename = getFilename(uploadRequestResponse.request());
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

  public void setPreflightEndpointInput(String url) {
    if (url.isEmpty())
      _preflightRequest = null;
    else {
      _preflightRequest = HttpRequest.httpRequestFromUrl(url);
      _preflightRequest = addCookiesTo(_preflightRequest);
    }
  }

  public HttpRequestResponse download(String newFilename) {
    // todo: handle preflight
    String updatedReq = _reDownloadRequest.toString().replace(_filename, newFilename);
    return download(HttpRequest.httpRequest(_reDownloadRequest.httpService(), updatedReq));
  }

  public HttpRequestResponse download(HttpRequest newRequest) {
    // todo: handle preflight
    Sender sender = new Sender(_api, newRequest);
    return sender.send();
  }

  public HttpRequestResponse download() {
    Sender sender = new Sender(_api, _reDownloadRequest);
    return sender.send();
  }

  public HttpRequestResponse sendPreflightReq() {
    Sender              sender          = new Sender(_api, _preflightRequest);
    HttpRequestResponse requestResponse = sender.send();
    _preflightResponse = requestResponse.response();
    return requestResponse;
  }

  public HttpRequestResponse sendReDownloadReq() {
    Sender              sender          = new Sender(_api, _reDownloadRequest);
    HttpRequestResponse requestResponse = sender.send();
    _reDownloadResponse = requestResponse.response();
    _isReady            = true;
    return requestResponse;
  }

  public void setStaticUrl(String url) {
    _staticUrl = url.replace(FILENAME_TAG, _filename);;
    _isReady = false;

    _reDownloadRequest = HttpRequest.httpRequestFromUrl(_staticUrl);
    _reDownloadRequest = addCookiesTo(_reDownloadRequest);
  }

  public String setStartMarker(String s) {
    _startMarker = s;
    return setDownloaderMarkerSelections();
  }

  public String setEndMarker(String s) {
    _endMarker = s;
    return setDownloaderMarkerSelections();
  }

  public boolean isUsed() {
    return _isReady;
  }

  public String getFilename() {
    return _filename;
  }

  public String getFileExtension() {
    return _filename.substring(_filename.lastIndexOf("."));
  };

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final CharSequence FILENAME_TAG = "${FILENAME}";

  private final MontoyaApi _api;
  private final String     _filename;
  private final HttpRequestResponse _uploadRequestResponse;

  private String       _startMarker       = "";
  private String       _endMarker         = "";
  private boolean      _replaceBackslash;
  private String       _prefix;
  private String       _suffix;
  private String       _staticUrl;
  private HttpRequest  _preflightRequest;
  private HttpResponse _preflightResponse;
  private HttpRequest  _reDownloadRequest = HttpRequest.httpRequest();
  private HttpResponse _reDownloadResponse;
  private boolean      _isReady           = false;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  /* This returns the first file name found within a multipart request. Will
   * not handle multiple filenames or when the actual filename required to
   * download the uploaded file is a different parameter */
  private String getFilename(HttpRequest request) {
    for (ParsedHttpParameter param : request.parameters()) {
      if (param.type() == HttpParameterType.MULTIPART_ATTRIBUTE)
        if (param.name().equalsIgnoreCase("filename"))
          return param.value();
    }
    _api.logging().logToError(
        "Failed to identify a \"filename\" parameter in the request");

    return "";
  }


  private String setDownloaderMarkerSelections() {
    StringBuilder selection = new StringBuilder();
    HttpResponse response = (preflightUsed()
        ? _preflightResponse : _uploadRequestResponse.response());

    // if both markers are not empty
    if (!_startMarker.isEmpty() && !_endMarker.isEmpty())
      selection.append(_startMarker).append(".*").append(_endMarker);
    // if only start marker is not empty
    else if (!_startMarker.isEmpty())
      selection.append(_startMarker);
    // if only end marker is not empty
    else if (!_endMarker.isEmpty())
      selection.append(_endMarker);

    Pattern pattern = Pattern.compile(selection.toString());
    Matcher matcher = pattern.matcher(response.toString());

    // if a match is found
    if (matcher.find()) {
      String match = matcher.group(0); // This will contain the matched string

      // if both markers are set
      if (!_startMarker.isEmpty() && !_endMarker.isEmpty()) {
        match = match.substring(_startMarker.length());
        match = match.substring(0, match.length() - _endMarker.length());

        // match could be a URL or a relative PATH
        if (!RequestUtils.isValidURL(match)) {
          HttpRequest request = _uploadRequestResponse.request();
          String url = request.url().replace(request.path(), "");
          url = url +match;
          if (!setReDownloadRequest(url)) {
            return "";
          }
        }
        else if (!setReDownloadRequest(match)) {
          return "";
        }
      }
      return match;
    }
    return "";
  }

  private boolean preflightUsed() {
    return _preflightRequest != null && !_preflightRequest.toString().isEmpty();
  }

  private boolean setReDownloadRequest(String match) {
    _isReady = false;
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

  private HttpRequest addCookiesTo(HttpRequest request) {
    for (HttpHeader header : _uploadRequestResponse.request().headers()) {
      // don't include the multipart content header
      if (!header.name().equalsIgnoreCase("Content-Type"))
        request = request.withAddedHeader(header);
    }
    return request;
  }
}
