package model;

import burp.api.montoya.http.message.HttpRequestResponse;

public class ScanModel {
    public ScanModel(BaseConfigModel baseConfigModel, HttpRequestResponse requestResponse) {
        _baseConfigModel = baseConfigModel.clone();
        _requestResponse = requestResponse;
    }
    
    public BaseConfigModel baseConfigModel(){
        return _baseConfigModel;
    }
    
    private BaseConfigModel     _baseConfigModel;
    private HttpRequestResponse _requestResponse;

}
