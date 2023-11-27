package controller.tabControllers;

import model.ConfigModel;
import view.tabs.GlobalConfigTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlobalConfigController {

    public GlobalConfigController(ConfigModel model) {
        this.model = model;
        view       = new GlobalConfigTab();
        addSaveButtonListener();
    }

    public String getTabName() {
        return view.getTabName();
    }

    public JPanel getTab() {
        return view.mainPanel;
    }

    private final ConfigModel     model;
    private final GlobalConfigTab view;

    private void addSaveButtonListener() {
        view.addSaveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setReplaceContentType(view.getReplaceContentType().isSelected());
                model.setReplaceFileName(view.getReplaceFileName().isSelected());
                model.setAddToLoggingTab(view.getAddToLoggingChkBox().isSelected());
                model.setReplaceFileSize(view.getReplaceFileSize().isSelected());
                model.setWgetCurlPayloads(view.getWgetCurlPayloads().isSelected());
                model.setSleepTime(Integer.parseInt(view.getSleepTime().getText()));
                model.setThrottleTime(Integer.parseInt(view.getThrottleValue().getText()));
            }
        });
    }
}
