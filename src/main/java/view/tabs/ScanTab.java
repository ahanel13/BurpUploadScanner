package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ScanTab extends JPanel {
    
    public ScanTab(HttpRequestResponse requestResponse) throws IOException {
        setLayout(new BorderLayout());
        
        // Initialize the left and right panels
        JScrollPane leftPanel      = initializeLeftPanel();
        JSplitPane  rightSplitPane = initializeRightSplitPane();
        
        // Create the main split pane with left and right components
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightSplitPane);
        
        add(mainSplitPane, BorderLayout.CENTER);
    }
    
    private BaseConfigTemplate baseConfigTemplate;
    private JPanel             scanTabConfig;
    
    private JScrollPane initializeLeftPanel() throws IOException {
        // Main Left Pane
        scanTabConfig = new JPanel();
        scanTabConfig.setLayout(new BoxLayout(scanTabConfig, BoxLayout.Y_AXIS));
        
        baseConfigTemplate = new BaseConfigTemplate();
        // Add more components as needed
        scanTabConfig.add(baseConfigTemplate);
        
        // Wrap in JScrollPane for scrollability
        return new JScrollPane(scanTabConfig);
    }
    
    private JSplitPane initializeRightSplitPane() {
        // Action Section (Upper Right Side)
        JPanel rightTopPanel = new JPanel();
        
        // Request editor Section (Lower Right Side)
        JPanel rightBottomPanel = new JPanel();
        
        // Splitting the right side vertically
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, rightTopPanel, rightBottomPanel);
        rightSplitPane.setDividerLocation(0.33);
        rightSplitPane.setResizeWeight(0.25);
        
        return rightSplitPane;
    }
}
