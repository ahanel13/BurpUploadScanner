package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import model.utilities.RequestUtils;
import view.templates.ActionPanelTemplate;
import view.templates.BaseConfigTemplate;
import view.templates.ReDownloaderTemplate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
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
        
        // view only listeners
        _linkPreflightInput2Btn();
        _linkStaticUrlInput2DownloaderBtn();
    }
    
    
    ////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////
    public BaseConfigTemplate baseConfigTemplate() {return _baseConfig;}
    
    // REDownLoader GETTERS
    ////////////////////////////////////////
    public String preflightEndpoint() {return _reDownloader.preflightEndpointInput.getText();}
    public boolean replaceBackslash() {return _reDownloader.replaceBackslash.isSelected();}
    public String getStartMarker()    {return _reDownloader.startMarker.getText();}
    public String getEndMarker()      {return _reDownloader.endMarker.getText();}
    public String getPrefix()         {return _reDownloader.prefix.getText();}
    public String getSuffix()         {return _reDownloader.suffix.getText();}
    public String getStaticUrl()      {return _reDownloader.staticUrl.getText();    }
    
    // Listener SETTERS
    ////////////////////////////////////////
    public void addSendPreflightReqListener(ActionListener l)     { _actionPanel.preflightRequestBtn.addActionListener(l); }
    public void addPreflightEndpointListener(DocumentListener l)  { _reDownloader.preflightEndpointInput.getDocument().addDocumentListener(l); }
    public void addReplaceBackslashListener(ActionListener l)     { _reDownloader.replaceBackslash.addActionListener(l); }
    public void addStartMarkerListener(DocumentListener l)        { _reDownloader.startMarker.getDocument().addDocumentListener(l); }
    public void addEndMarkerListener(DocumentListener l)          { _reDownloader.endMarker.getDocument().addDocumentListener(l); }
    public void addPrefixListener(ActionListener l)               { _reDownloader.prefix.addActionListener(l); }
    public void addSuffixListener(ActionListener l)               { _reDownloader.suffix.addActionListener(l); }
    public void addStaticUrlListener(ActionListener l)            { _reDownloader.staticUrl.addActionListener(l); }
    public void setStartMarkerBackground(Color color)             { _reDownloader.startMarker.setBackground(color); }
    public void setEndMarkerBackground(Color color)               { _reDownloader.endMarker.setBackground(color); }
    public void addSendDownloadReqListener(ActionListener l)      { _actionPanel.reDownloaderBtn.addActionListener(l); }
    
    // HttpEditor Update Functions
    ////////////////////////////////////////
    public void updatePreflightWindows(HttpRequestResponse requestResponse) {
        _prefliReqEditor.setRequest(requestResponse.request());
        _prefliResEditor.setResponse(requestResponse.response());
        _editorPanels.setEnabledAt(editorTabs.preflightRequest.ordinal(), true);
        _editorPanels.setEnabledAt(editorTabs.preflightResponse.ordinal(), true);
    }
    
    public void updatePreflightWindow(HttpRequest request) {
        _prefliReqEditor.setRequest(request);
        _editorPanels.setEnabledAt(editorTabs.preflightRequest.ordinal(), true);
        _editorPanels.updateUI();
    }
    
    public void updateReDownloadWindows(HttpRequestResponse requestResponse) {
        _downReqEditor.setRequest(requestResponse.request());
        _downResEditor.setResponse(requestResponse.response());
        _editorPanels.setEnabledAt(editorTabs.downloadRequest.ordinal(), true);
        _editorPanels.setEnabledAt(editorTabs.downloadResponse.ordinal(), true);
    }
    
    public void setReDownloadEditor(HttpRequest request) {
        _downReqEditor.setRequest(request);
        if (request.equals(HttpRequest.httpRequest()))
            _editorPanels.setEnabledAt(editorTabs.downloadRequest.ordinal(), false);
        else {
            _editorPanels.setEnabledAt(editorTabs.downloadRequest.ordinal(), true);
            _actionPanel.reDownloaderBtn.setEnabled(true);
        }
        _editorPanels.updateUI();
    }
    
    public void setReDownloadSelection(String match) {
        HttpResponseEditor response  = (preflightEndpoint().isBlank()
            ? _origResEditor : _prefliResEditor);
        
        response.setSearchExpression(match);
    }
    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    ////////////////////////////////////////
    // PRIVATE FIELDS
    ////////////////////////////////////////
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
        downloadRequest, downloadResponse
    }
    
    ////////////////////////////////////////
    // PRIVATE METHODS
    ////////////////////////////////////////
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
                if (!RequestUtils.isValidURL(_reDownloader.preflightEndpointInput.getText())) {
                    _reDownloader.preflightEndpointInput.setBackground(Color.PINK);
                    _actionPanel.preflightRequestBtn.setEnabled(false);
                } else {
                    _reDownloader.preflightEndpointInput.setBackground(Color.WHITE);
                    _actionPanel.preflightRequestBtn.setEnabled(true);
                }
            }
        });
    }
    
    private void _linkStaticUrlInput2DownloaderBtn() {
        _reDownloader.staticUrl.getDocument().addDocumentListener(new DocumentListener() {
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
                if (!RequestUtils.isValidURL(_reDownloader.staticUrl.getText())) {
                    _reDownloader.staticUrl.setBackground(Color.PINK);
                    _actionPanel.reDownloaderBtn.setEnabled(false);
                } else {
                    _reDownloader.staticUrl.setBackground(Color.WHITE);
                    _actionPanel.reDownloaderBtn.setEnabled(true);
                }
            }
        });
    }
    
    // UI initalization
    ////////////////////////////////////////
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
}
