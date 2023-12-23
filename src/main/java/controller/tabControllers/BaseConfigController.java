package controller.tabControllers;

import model.BaseConfigModel;
import view.tabs.BaseConfigTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseConfigController{

    public BaseConfigController(BaseConfigModel baseConfigModel, BaseConfigTab baseConfigTab) {
        model = baseConfigModel;
        view  = baseConfigTab;
        addSaveButtonListener();
        addResetButtonListener();
        updateView();
    }
    
    public String getTabName() {
        return BaseConfigTab.TAB_NAME;
    }

    public JPanel getTab() {
        return view;
    }

    private final BaseConfigModel model;
    private final BaseConfigTab   view;
    
    private void updateView(){
        view.setReplaceFileName     (model.replaceFileName());
        view.setReplaceFileSize     (model.replaceFileSize());
        view.setReplaceContentType  (model.replaceContentType());
        view.setAddToLoggingChkBox  (model.addToLoggingTab());
        view.setWgetCurlPayloads    (model.wgetCurlPayloads());
        view.setSleepTime           (model.sleepTime());
        view.setThrottleValue       (model.throttleTime());
    }

    private void addSaveButtonListener() {
        view.addSaveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // File Settings
                model.setReplaceContentType             (view.getReplaceContentType().isSelected());
                model.setReplaceFileName                (view.getReplaceFileName().isSelected());
                model.setReplaceFileSize                (view.getReplaceFileSize().isSelected());
                
                model.setWgetCurlPayloads               (view.getWgetCurlPayloads().isSelected());
                model.setSleepTime(Short.parseShort     (view.getSleepTime()));
                
                model.setThrottleTime(Short.parseShort  (view.getThrottleValue()));
                model.setAddToLoggingTab                (view.getAddToLoggingChkBox().isSelected());
                
                model.persist();
                
                //Todo: add error handling when a valid short cannot be cast.
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
