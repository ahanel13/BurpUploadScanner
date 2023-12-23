package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import view.templates.ActionPanelTemplate;
import view.templates.BaseConfigTemplate;
import view.templates.ReDownloaderTemplate;

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
        return _baseConfigTemplate;
    }
    
    private final HttpRequestResponse  _requestResponse;
    private final UserInterface        _apiUI;
    private       JPanel               _actionPanel;
    private       BaseConfigTemplate   _baseConfigTemplate;
    private       ReDownloaderTemplate _reDownloaderTemplate;
    private       JPanel               _scanTabConfig;
    private       JTabbedPane          _editorPanels;
    private enum editorTabs{
        uploadRequest, uploadResponse, preflightRequest, preflightResponse,
        downloadRequest, DownloadResponse
    }
    
    private JScrollPane initializeLeftPanel() throws IOException {
        // Main Left Pane
        _scanTabConfig = new JPanel();
        _scanTabConfig.setLayout(new BoxLayout(_scanTabConfig, BoxLayout.Y_AXIS));
        
        _baseConfigTemplate   = new BaseConfigTemplate();
        _reDownloaderTemplate = new ReDownloaderTemplate();
        
        // Add more components as needed
        _scanTabConfig.add(_baseConfigTemplate);
        _scanTabConfig.add(_reDownloaderTemplate);
        
        // Wrap in JScrollPane for scrollability
        JScrollPane pane = new JScrollPane(_scanTabConfig);
        pane.getVerticalScrollBar().setUnitIncrement(20);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return pane;
    }
    
    private JSplitPane initializeRightSplitPane() {
        // Action Section (Upper Right Side)
        _actionPanel = new ActionPanelTemplate();
        
        // Request editor Section (Lower Right Side)
        _editorPanels = getRequestEditorPanels();
        
        // Splitting the right side vertically
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _actionPanel, _editorPanels);
        rightSplitPane.setDividerLocation(0.20);
        
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
