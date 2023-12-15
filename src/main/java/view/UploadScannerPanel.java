package view;

import burp.api.montoya.http.message.HttpRequestResponse;
import view.tabs.ScanTab;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UploadScannerPanel extends JTabbedPane {
    public UploadScannerPanel() {
        scanTabList = new ArrayList<>();
    }

    public ScanTab addScanTab(HttpRequestResponse requestResponse) throws IOException{
        ScanTab tempScanTab = new ScanTab(requestResponse);
        String  scanTabName = "Scan " + (scanTabList.size() + 1);
        scanTabList.add(tempScanTab);
        addTab(scanTabName, tempScanTab);
        return tempScanTab;
    }

    private final List<ScanTab> scanTabList;
}
