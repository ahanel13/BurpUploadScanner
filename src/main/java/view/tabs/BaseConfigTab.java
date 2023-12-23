package view.tabs;

import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseConfigTab extends JPanel {
  public static final String TAB_NAME = "Global Config";

  public BaseConfigTab() throws IOException {
    setLayout(new BorderLayout());
    _baseConfigTemplate = new BaseConfigTemplate();
    JScrollPane pane = new JScrollPane(_baseConfigTemplate);
    pane.getVerticalScrollBar().setUnitIncrement(20);
    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    add(pane);
    addFooterUi();
  }
  
  public AbstractButton getReplaceContentType() { return _baseConfigTemplate.getReplaceContentType(); }
  public AbstractButton getReplaceFileName()    { return _baseConfigTemplate.getReplaceFileName(); }
  public AbstractButton getReplaceFileSize()    { return _baseConfigTemplate.getReplaceFileSize(); }
  public String         getSleepTime()          { return _baseConfigTemplate.getSleepTime(); }
  public AbstractButton getWgetCurlPayloads()   { return _baseConfigTemplate.getWgetCurlPayloads(); }
  public String         getThrottleValue()      { return _baseConfigTemplate.getThrottleValue(); }
  public AbstractButton getAddToLoggingChkBox() { return _baseConfigTemplate.getAddToLoggingChkBox(); }
  
  public void setReplaceFileName(boolean b)     { _baseConfigTemplate.setReplaceFileName(b); }
  public void setReplaceFileSize(boolean b)     { _baseConfigTemplate.setReplaceFileSize(b); }
  public void setAddToLoggingChkBox(boolean b)  { _baseConfigTemplate.setReplaceContentType(b); }
  public void setWgetCurlPayloads(boolean b)           { _baseConfigTemplate.setWgetCurlPayloads(b); }
  public void setSleepTime(short i)                    { _baseConfigTemplate.setSleepTime(i); }
  public void setThrottleValue(short i)                { _baseConfigTemplate.setThrottleValue(i); }
  public void setReplaceContentType(boolean b)         { _baseConfigTemplate.setAddToLoggingChkBox(b); }
  
  public void addSaveButtonListener(ActionListener l)  { _saveBtn.addActionListener(l); }
  public void addResetButtonListener(ActionListener l) { _resetBtn.addActionListener(l); }
  
  private final BaseConfigTemplate _baseConfigTemplate;
  private       JPanel             _footerButtons;
  private       JButton            _resetBtn;
  private       JButton            _saveBtn;

  private void addFooterUi() {
    _footerButtons = new JPanel();
    _footerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

    _resetBtn = new JButton();
    _resetBtn.setText("Reset");
    _footerButtons.add(_resetBtn);

    _saveBtn = new JButton();
    _saveBtn.setText("Save");
    _footerButtons.add(_saveBtn);

    add(_footerButtons, BorderLayout.SOUTH);
  }
}
