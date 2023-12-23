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
        _view.baseConfigTemplate().setReplaceFileName     (_model.baseConfigModel().replaceFileName());
        _view.baseConfigTemplate().setReplaceFileSize     (_model.baseConfigModel().replaceFileSize());
        _view.baseConfigTemplate().setReplaceContentType  (_model.baseConfigModel().replaceContentType());
        _view.baseConfigTemplate().setAddToLoggingChkBox  (_model.baseConfigModel().addToLoggingTab());
        _view.baseConfigTemplate().setWgetCurlPayloads    (_model.baseConfigModel().wgetCurlPayloads());
        _view.baseConfigTemplate().setSleepTime           (_model.baseConfigModel().sleepTime());
        _view.baseConfigTemplate().setThrottleValue       (_model.baseConfigModel().throttleTime());
        
    }
    
    private final ScanTab   _view;
    private final ScanModel _model;
}
