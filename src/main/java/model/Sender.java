package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

import javax.swing.*;

public class Sender extends SwingWorker<HttpRequestResponse, Void> {

  public Sender(MontoyaApi api, HttpRequest request) {
    _api     = api;
    _request = request;
  }

  @Override
  protected HttpRequestResponse doInBackground() throws Exception {
    _response = sendHttpRequest(_request); // Store the response
    return null; // We're using Void as the first type parameter, so return null
  }

  private final HttpRequest _request;
  private final MontoyaApi  _api;
  private HttpRequestResponse _response;

  public HttpRequestResponse getResponse() {
    while (!isDone()) {
      _api.logging().raiseInfoEvent("Sender request task not done yet");
    }
    return _response;
  }

  private HttpRequestResponse sendHttpRequest(HttpRequest request) {
    return _api.http().sendRequest(request);
  }
}
