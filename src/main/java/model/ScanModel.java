package model;

import burp.api.montoya.http.message.HttpRequestResponse;

public class ScanModel {
    public ScanModel(ScanConfigModel globalScanConfigModel, HttpRequestResponse requestResponse) {
        _configModel     = globalScanConfigModel;
        _requestResponse = requestResponse;
    }

    private ScanConfigModel     _configModel;
    private HttpRequestResponse _requestResponse;

}
