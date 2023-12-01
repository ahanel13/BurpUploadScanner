package view;

import burp.api.montoya.http.message.HttpRequestResponse;
import view.tabs.ScanTab;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class UploadScannerPanel extends JPanel {
    public UploadScannerPanel() {
        super(new BorderLayout());
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        scanTabList = new ArrayList<>();
    }

    public ScanTab addScanTab(HttpRequestResponse requestResponse) {
        ScanTab tempScanTab = new ScanTab(requestResponse);
        String  scanTabName = "Scan " + (scanTabList.size() + 1);
        scanTabList.add(tempScanTab);
        addTab(scanTabName, tempScanTab);
        return tempScanTab;
    }

    public void addTab(String tabName, JPanel newTab) {
        tabbedPane.addTab(tabName, newTab);
    }

    private final JTabbedPane   tabbedPane;
    private final List<ScanTab> scanTabList;
}
