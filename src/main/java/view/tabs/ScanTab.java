package view.tabs;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;
import view.templates.ActionPanelTemplate;
import view.templates.BaseConfigTemplate;
import view.templates.DownloaderTemplate;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ScanTab extends JPanel {

  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
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

  ////////////////////////////////////////
  // PUBLIC METHODS
  ////////////////////////////////////////
  public BaseConfigTemplate baseConfigTemplate() {return _baseConfig;}

  // REDownLoader GETTERS
  ////////////////////////////////////////
  public String preflightEndpoint() {return _downloaderComp.preflightEndpointInput.getText();}
  public boolean replaceBackslash() {return _downloaderComp.replaceBackslash.isSelected();}
  public String getStartMarker()    {return _downloaderComp.startMarker.getText();}
  public String getEndMarker()      {return _downloaderComp.endMarker.getText();}
  public String getPrefix()         {return _downloaderComp.prefix.getText();}
  public String getSuffix()         {return _downloaderComp.suffix.getText();}
  public String getStaticUrl()      {return _downloaderComp.staticUrl.getText();}

  // Listener SETTERS
  ////////////////////////////////////////
  public void addSendPreflightReqListener(ActionListener l) {_actionPanel.preflightRequestBtn.addActionListener(l);}
  public void addPreflightEndpointListener(DocumentListener l) {
    _downloaderComp.preflightEndpointInput.getDocument()
        .addDocumentListener(l);
  }
  public void addReplaceBackslashListener(ActionListener l) {_downloaderComp.replaceBackslash.addActionListener(l);}
  public void addStartMarkerListener(DocumentListener l) {
    _downloaderComp.startMarker.getDocument()
        .addDocumentListener(l);
  }
  public void addEndMarkerListener(DocumentListener l) {
    _downloaderComp.endMarker.getDocument()
        .addDocumentListener(l);
  }
  public void addPrefixListener(ActionListener l)          {_downloaderComp.prefix.addActionListener(l);}
  public void addSuffixListener(ActionListener l)          {_downloaderComp.suffix.addActionListener(l);}
  public void addStaticUrlListener(ActionListener l)       {_downloaderComp.staticUrl.addActionListener(l);}
  public void setStartMarkerBackground(Color color)        {_downloaderComp.startMarker.setBackground(color);}
  public void setEndMarkerBackground(Color color)          {_downloaderComp.endMarker.setBackground(color);}
  public void addSendDownloadReqListener(ActionListener l) {_actionPanel.downloaderBtn.addActionListener(l);}

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
    _actionPanel.preflightRequestBtn.setEnabled(true);
  }

  public void disablePreflightWindows() {
    _prefliReqEditor.setRequest(HttpRequest.httpRequest());
    _prefliResEditor.setSearchExpression("");
    _prefliResEditor.setResponse(HttpResponse.httpResponse());
    _prefliReqEditor.setSearchExpression("");
    _editorPanels.setEnabledAt(editorTabs.preflightRequest.ordinal(), false);
    _editorPanels.setEnabledAt(editorTabs.preflightResponse.ordinal(), false);
    _editorPanels.updateUI();
    _actionPanel.preflightRequestBtn.setEnabled(false);
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
      _actionPanel.downloaderBtn.setEnabled(true);
    }
    _editorPanels.updateUI();
  }

  public void setReDownloadSelection(String match) {
    HttpResponseEditor response = (preflightEndpoint().isBlank()
        ? _origResEditor : _prefliResEditor);

    response.setSearchExpression(match);
  }

  // Misc
  ////////////////////////////////////////
  public void displayMessage(String message) {JOptionPane.showMessageDialog(this, message);}
  public void setStaticUrlBackground(Color color)         {_downloaderComp.staticUrl.setBackground(color);}
  public void setPreflightEndpointBackground(Color color) {_downloaderComp.preflightEndpointInput.setBackground(color);}

  public void triggerParsers() {
    _triggerActionOn(_downloaderComp.startMarker);
    _triggerActionOn(_downloaderComp.endMarker);
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final HttpRequestResponse _requestResponse;
  private final UserInterface       _apiUI;
  private       ActionPanelTemplate _actionPanel;
  private       BaseConfigTemplate  _baseConfig;
  private       DownloaderTemplate  _downloaderComp;
  private       JPanel              _scanTabConfig;
  private       JTabbedPane         _editorPanels;
  private       HttpRequestEditor   _origReqEditor;
  private       HttpResponseEditor  _origResEditor;
  private       HttpRequestEditor   _prefliReqEditor;
  private       HttpResponseEditor  _prefliResEditor;
  private       HttpRequestEditor   _downReqEditor;
  private       HttpResponseEditor  _downResEditor;


  private enum editorTabs {
    uploadRequest, uploadResponse, preflightRequest, preflightResponse,
    downloadRequest, downloadResponse
  }

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////

  // UI initalization
  ////////////////////////////////////////
  private JScrollPane initializeLeftPanel() throws IOException {
    // Main Left Pane
    _scanTabConfig = new JPanel();
    _scanTabConfig.setLayout(new BoxLayout(_scanTabConfig, BoxLayout.Y_AXIS));

    _baseConfig     = new BaseConfigTemplate();
    _downloaderComp = new DownloaderTemplate();

    // Add more components as needed
    _scanTabConfig.add(_baseConfig);
    _scanTabConfig.add(_downloaderComp);

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

  private JTabbedPane getRequestEditorPanels() {
    JTabbedPane editorPane = new JTabbedPane();
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

    for (int i = 2; i < editorPane.getTabCount(); i++) {
      editorPane.setEnabledAt(i, false);
    }

    return editorPane;
  }

  // Misc
  ////////////////////////////////////////
  private static void _triggerActionOn( JTextField component) {
    Document document = component.getDocument();
    document.putProperty("suppressNotify", true); // Suppress firing events temporarily
    try {
      document.insertString(document.getLength(), "", null);
      document.remove(0, 0);
    }catch (BadLocationException e) {throw new RuntimeException(e);}
    document.putProperty("suppressNotify", false); // Allow firing events again
  }
}
