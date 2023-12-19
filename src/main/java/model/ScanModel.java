package model;

import burp.api.montoya.http.message.HttpRequestResponse;

public class ScanModel {
    public ScanModel(ScanConfigModel scanConfigModel, HttpRequestResponse requestResponse) {
        _scanConfigModel = scanConfigModel.clone();
        _requestResponse = requestResponse;
    }

    private ScanConfigModel     _configModel;
    private HttpRequestResponse _requestResponse;

}
