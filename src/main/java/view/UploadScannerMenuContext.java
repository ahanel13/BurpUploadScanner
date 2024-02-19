package view;

import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UploadScannerMenuContext implements ContextMenuItemsProvider {

  public UploadScannerMenuContext() {
    // this maintains a single JMenuItem which will have a single event listener
    retrieveUploadRequest = new JMenuItem("Send to upload scanner");
  }

  public void addEventListenerToMenuItem(ActionListener listener) {
    retrieveUploadRequest.addActionListener(listener);
  }

  @Override
  public List<Component> provideMenuItems(ContextMenuEvent event) {
    List<Component> menuItemList = new ArrayList<>();

    if (event.isFromTool(ToolType.PROXY, ToolType.TARGET, ToolType.LOGGER)) {
      requestResponse = event.messageEditorRequestResponse().isPresent() ? event.messageEditorRequestResponse().get()
          .requestResponse() : event.selectedRequestResponses().get(0);
      menuItemList.add(retrieveUploadRequest);
    }

    return menuItemList;
  }

  public HttpRequestResponse getRequestResponse() {
    return requestResponse;
  }
  private final JMenuItem           retrieveUploadRequest;
  private       HttpRequestResponse requestResponse;
}
