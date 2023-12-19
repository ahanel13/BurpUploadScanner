package controller.tabControllers;

import model.ScanModel;
import view.tabs.ScanTab;

public class ScanTabController {
    public ScanTabController(ScanModel scanModel, ScanTab scanTabView) {
        _model = scanModel;
        _view  = scanTabView;
        updateView();
    }
    
    private void updateView(){
        _view.baseConfigTemplate().setReplaceFileName     (_model.scanConfigModel().replaceFileName());
        _view.baseConfigTemplate().setReplaceFileSize     (_model.scanConfigModel().replaceFileSize());
        _view.baseConfigTemplate().setReplaceContentType  (_model.scanConfigModel().replaceContentType());
        _view.baseConfigTemplate().setAddToLoggingChkBox  (_model.scanConfigModel().addToLoggingTab());
        _view.baseConfigTemplate().setWgetCurlPayloads    (_model.scanConfigModel().wgetCurlPayloads());
        _view.baseConfigTemplate().setSleepTime           (_model.scanConfigModel().sleepTime());
        _view.baseConfigTemplate().setThrottleValue       (_model.scanConfigModel().throttleTime());
    }
    
    private final ScanTab   _view;
    private final ScanModel _model;
}
