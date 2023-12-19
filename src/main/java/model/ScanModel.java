package model;

import burp.api.montoya.http.message.HttpRequestResponse;

public class ScanModel {
    public ScanModel(ScanConfigModel scanConfigModel, HttpRequestResponse requestResponse) {
        _scanConfigModel = scanConfigModel.clone();
        _requestResponse = requestResponse;
    }
    
    public ScanConfigModel scanConfigModel(){
        return _scanConfigModel;
    }
    
    private ScanConfigModel     _scanConfigModel;
    private HttpRequestResponse _requestResponse;

}
