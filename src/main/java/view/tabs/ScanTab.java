package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ScanTab extends JPanel {
    
    public ScanTab(HttpRequestResponse httpRequestResponse, UserInterface userInterface) throws IOException {
        _requestResponse = httpRequestResponse;
        _apiUI           = userInterface;
        setLayout(new BorderLayout());
        
        // Initialize the left and right panels
        JScrollPane leftPanel      = initializeLeftPanel();
        JSplitPane  rightSplitPane = initializeRightSplitPane();
        
        // Create the main split pane with left and right components
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightSplitPane);
        
        add(mainSplitPane, BorderLayout.CENTER);
    }
    
    public BaseConfigTemplate baseConfigTemplate(){
        return baseConfigTemplate;
    }
    
    private enum editorTabs{
        uploadRequest, uploadResponse, preflightRequest, preflightResponse,
        downloadRequest, DownloadResponse
    }
    private final HttpRequestResponse _requestResponse;
    private final UserInterface       _apiUI;
    private BaseConfigTemplate baseConfigTemplate;
    private JPanel             _scanTabConfig;
    private JTabbedPane        _editorPanels;
    
    private JScrollPane initializeLeftPanel() throws IOException {
        // Main Left Pane
        _scanTabConfig = new JPanel();
        _scanTabConfig.setLayout(new BoxLayout(_scanTabConfig, BoxLayout.Y_AXIS));
        
        baseConfigTemplate = new BaseConfigTemplate();
        // Add more components as needed
        _scanTabConfig.add(baseConfigTemplate);
        
        // Wrap in JScrollPane for scrollability
        return new JScrollPane(_scanTabConfig);
    }
    
    private JSplitPane initializeRightSplitPane() {
        // Action Section (Upper Right Side)
        JPanel rightTopPanel = new JPanel();
        
        // Request editor Section (Lower Right Side)
        _editorPanels = getRequestEditorPanels();
        
        // Splitting the right side vertically
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, rightTopPanel, _editorPanels);
        rightSplitPane.setDividerLocation(0.33);
        rightSplitPane.setResizeWeight(0.25);
        
        return rightSplitPane;
    }
    
    private JTabbedPane getRequestEditorPanels(){
        JTabbedPane        editorPane     = new JTabbedPane();
        HttpRequestEditor  requestEditor  = _apiUI.createHttpRequestEditor();
        HttpResponseEditor responseEditor = _apiUI.createHttpResponseEditor();
        
        requestEditor.setRequest(_requestResponse.request());
        responseEditor.setResponse(_requestResponse.response());
        
        editorPane.addTab("Upload Request", requestEditor.uiComponent());
        editorPane.addTab("Upload Response", responseEditor.uiComponent());
        editorPane.addTab("PreFlight Request", new JPanel());
        editorPane.addTab("Preflight Response", new JPanel());
        editorPane.addTab("Redownload Request", new JPanel());
        editorPane.addTab("Redownload Response", new JPanel());
        
        for(int i = 2; i < editorPane.getTabCount(); i++){
            editorPane.setEnabledAt(i, false);
        }
        
        return  editorPane;
    }
}
