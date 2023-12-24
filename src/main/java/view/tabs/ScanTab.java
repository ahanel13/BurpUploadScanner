package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import view.templates.ActionPanelTemplate;
import view.templates.BaseConfigTemplate;
import view.templates.ReDownloaderTemplate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
        
        // view only listeners
        _linkPreflightInput2Btn();
    }
    
    // PUBLIC METHODS //
    public BaseConfigTemplate baseConfigTemplate()        {return _baseConfig;}
    
    // REDownLoader Functions
    public String preflightEndpoint() {return _reDownloader.preflightEndpointInput.getText();}
    public boolean replaceBackslash() {return _reDownloader.replaceBackslash.isSelected();}
    public String getStartMarker()    {return _reDownloader.startMarker.getText();}
    public String getEndMarker()      {return _reDownloader.endMarker.getText();}
    public String getPrefix()         {return _reDownloader.prefix.getText();}
    public String getSuffix()         {return _reDownloader.suffix.getText();}
    public String getStaticUrl()      {return _reDownloader.staticUrl.getText();    }
    
    public void addSendPreflightReqListener(ActionListener l){_actionPanel.preflightRequestBtn.addActionListener(l);}
    
    public void addPreflightEndpointListener(DocumentListener l) {
        _reDownloader.preflightEndpointInput.getDocument().addDocumentListener(l);
    }
    
    public void setupReplaceBackslashListener(ActionListener l) {
        _reDownloader.replaceBackslash.addActionListener(l);
    }
    
    public void setupStartMarkerListener(ActionListener l) {
        _reDownloader.startMarker.addActionListener(l);
    }
    
    public void setupEndMarkerListener(ActionListener l) {
        _reDownloader.endMarker.addActionListener(l);
    }
    
    public void setupPrefixListener(ActionListener l) {
        _reDownloader.prefix.addActionListener(l);
    }
    
    public void setupSuffixListener(ActionListener l) {
        _reDownloader.suffix.addActionListener(l);
    }
    
    public void setupStaticUrlListener(ActionListener l) {
        _reDownloader.staticUrl.addActionListener(l);
    }
    
    public void updatePreflightWindows(HttpRequestResponse requestResponse) {
        _prefliReqEditor.setRequest(requestResponse.request());
        _prefliResEditor.setResponse(requestResponse.response());
        _editorPanels.setEnabledAt(editorTabs.preflightRequest.ordinal(), true);
        _editorPanels.setEnabledAt(editorTabs.preflightResponse.ordinal(), true);
    }
    
    public void updatePreflightWindow(HttpRequest request) {
        _prefliReqEditor.setRequest(request);
        _editorPanels.setEnabledAt(editorTabs.preflightRequest.ordinal(), true);
    }
    
    // PRIVATE FIELDS
    private final HttpRequestResponse  _requestResponse;
    private final UserInterface        _apiUI;
    private       ActionPanelTemplate  _actionPanel;
    private       BaseConfigTemplate   _baseConfig;
    private       ReDownloaderTemplate _reDownloader;
    private       JPanel               _scanTabConfig;
    private       JTabbedPane          _editorPanels;
    private       HttpRequestEditor    _origReqEditor;
    private       HttpResponseEditor   _origResEditor;
    private       HttpRequestEditor    _prefliReqEditor;
    private       HttpResponseEditor   _prefliResEditor;
    private       HttpRequestEditor    _downReqEditor;
    private       HttpResponseEditor   _downResEditor;
    
    
    private enum editorTabs{
        uploadRequest, uploadResponse, preflightRequest, preflightResponse,
        downloadRequest, DownloadResponse
    }
    
    private void _linkPreflightInput2Btn() {
        _reDownloader.preflightEndpointInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                validateURL();
            }
            public void removeUpdate(DocumentEvent e) {
                validateURL();
            }
            public void insertUpdate(DocumentEvent e) {
                validateURL();
            }
            private void validateURL() {
                if (!isValidURL(_reDownloader.preflightEndpointInput.getText())) {
                    _reDownloader.preflightEndpointInput.setBackground(Color.PINK);
                    _actionPanel.preflightRequestBtn.setEnabled(false);
                } else {
                    _reDownloader.preflightEndpointInput.setBackground(Color.WHITE);
                    _actionPanel.preflightRequestBtn.setEnabled(true);
                }
            }
        });
    }
    
    private JScrollPane initializeLeftPanel() throws IOException {
        // Main Left Pane
        _scanTabConfig = new JPanel();
        _scanTabConfig.setLayout(new BoxLayout(_scanTabConfig, BoxLayout.Y_AXIS));
        
        _baseConfig   = new BaseConfigTemplate();
        _reDownloader = new ReDownloaderTemplate();
        
        // Add more components as needed
        _scanTabConfig.add(_baseConfig);
        _scanTabConfig.add(_reDownloader);
        
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
    
    // PRIVATE METHODS //
    private JTabbedPane getRequestEditorPanels(){
        JTabbedPane        editorPane     = new JTabbedPane();
        _origReqEditor   = _apiUI.createHttpRequestEditor();
        _origResEditor   = _apiUI.createHttpResponseEditor();
        _prefliReqEditor = _apiUI.createHttpRequestEditor();
        _prefliResEditor = _apiUI.createHttpResponseEditor();
        _downReqEditor   = _apiUI.createHttpRequestEditor();
        _downResEditor   = _apiUI.createHttpResponseEditor();
        
        _origReqEditor.setRequest(_requestResponse.request());
        _origResEditor.setResponse(_requestResponse.response());
        
        editorPane.addTab("Upload Request", _origReqEditor.uiComponent());
        editorPane.addTab("Upload Response", _origResEditor.uiComponent());
        editorPane.addTab("Preflight Request", _prefliReqEditor.uiComponent());
        editorPane.addTab("Preflight Response", _prefliResEditor.uiComponent());
        editorPane.addTab("Redownload Request", _downReqEditor.uiComponent());
        editorPane.addTab("Redownload Response", _downResEditor.uiComponent());
        
        for(int i = 2; i < editorPane.getTabCount(); i++){
            editorPane.setEnabledAt(i, false);
        }
        
        return  editorPane;
    }
    
    private boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
