package model;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class Sender extends SwingWorker<HttpRequestResponse, Void> {

  public Sender(MontoyaApi api, HttpRequest request) {
    _api     = api;
    _request = request;
  }

  public HttpRequestResponse send() {
    super.execute();
    try {
      return super.get();
    }
    catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected HttpRequestResponse doInBackground() {
    return _api.http().sendRequest(_request);
  }

  private final HttpRequest _request;
  private final MontoyaApi  _api;
}
