package controller.tabControllers;

import model.ScanModel;
import view.tabs.ScanTab;

public class ScanTabController {
    public ScanTabController(ScanModel scanModel, ScanTab scanTabView) {
        _model = scanModel;
        _view  = scanTabView;
    }

    private ScanTab   _view;
    private ScanModel _model;
}
