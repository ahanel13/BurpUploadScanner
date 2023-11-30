package controller.tabControllers;

import model.ScanConfigModel;
import view.templates.IntellijBaseConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ScanConfigController {

    public ScanConfigController(ScanConfigModel scanConfigModel, IntellijBaseConfig defaultScanOptionsView) {
        model = scanConfigModel;
        view  = defaultScanOptionsView;
        addSaveButtonListener();
        addResetButtonListener();
        updateView();
    }

    public String getTabName() {
        return view.getTabName();
    }

    public JPanel getTab() {
        return view.mainPanel;
    }

    private final ScanConfigModel    model;
    private final IntellijBaseConfig view;

    private void updateView(){
        view.setReplaceContentType(model.replaceContentType());
        view.setReplaceFileName(model.replaceFileName());
        view.setReplaceFileSize(model.replaceFileSize());
        view.setAddToLoggingChkBox(model.addToLoggingTab());
        view.setWgetCurlPayloads(model.wgetCurlPayloads());
        view.setSleepTime(model.sleepTime());
        view.setThrottleValue(model.throttleTime());
    }

    private void addSaveButtonListener() {
        view.addSaveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setReplaceContentType(view.getReplaceContentType().isSelected());
                model.setReplaceFileName(view.getReplaceFileName().isSelected());
                model.setAddToLoggingTab(view.getAddToLoggingChkBox().isSelected());
                model.setReplaceFileSize(view.getReplaceFileSize().isSelected());
                model.setWgetCurlPayloads(view.getWgetCurlPayloads().isSelected());
                model.setSleepTime(Short.parseShort(view.getSleepTime().getText()));
                model.setThrottleTime(Short.parseShort(view.getThrottleValue().getText()));
            }
        });
    }

    private void addResetButtonListener() {
        view.addResetButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.reset();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                updateView();
            }
        });
    }
}
