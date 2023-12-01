package model;

import burp.api.montoya.http.message.HttpRequestResponse;

public class ScanModel {
    public ScanModel(ConfigModel globalScanConfigModel, HttpRequestResponse requestResponse) {
        _configModel     = globalScanConfigModel;
        _requestResponse = requestResponse;
    }

    private ConfigModel         _configModel;
    private HttpRequestResponse _requestResponse;

}
