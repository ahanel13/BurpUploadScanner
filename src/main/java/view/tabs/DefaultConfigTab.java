package view.tabs;

import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DefaultConfigTab extends JPanel {

  public DefaultConfigTab() throws IOException {
    setLayout(new BorderLayout());
    _baseConfigTemplate = new BaseConfigTemplate();
    add(_baseConfigTemplate, BorderLayout.CENTER);
    addFooterUi();
  }

  public static final String TAB_NAME = "Global Config";

  private final BaseConfigTemplate _baseConfigTemplate;

  public void setReplaceFileName(boolean b) {
    _baseConfigTemplate.setReplaceFileSize(b);
  }

  public void setReplaceFileSize(boolean b) {
  _baseConfigTemplate.setReplaceFileSize(b);
  }

  public void setAddToLoggingChkBox(boolean b) {
  _baseConfigTemplate.setReplaceContentType(b);
  }

  public void setWgetCurlPayloads(boolean b) {
  _baseConfigTemplate.setWgetCurlPayloads(b);
  }

  public void setSleepTime(short i) {
  _baseConfigTemplate.setSleepTime(i);
  }

  public void setThrottleValue(short i) {
  _baseConfigTemplate.setThrottleValue(i);
  }

  public void addSaveButtonListener(ActionListener listener) {
    _saveBtn.addActionListener(listener);
  }
  public void addResetButtonListener(ActionListener listener) {
    _resetBtn.addActionListener(listener);
  }

  public AbstractButton getReplaceContentType() {
    return _baseConfigTemplate.getReplaceContentType();
  }

  public AbstractButton getReplaceFileName() {
    return _baseConfigTemplate.getReplaceFileName();
  }

  public AbstractButton getAddToLoggingChkBox() {
    return _baseConfigTemplate.getAddToLoggingChkBox();
  }

  public AbstractButton getReplaceFileSize() {
    return _baseConfigTemplate.getReplaceFileName();
  }

  public AbstractButton getWgetCurlPayloads() {
    return _baseConfigTemplate.getWgetCurlPayloads();
  }

  public String getSleepTime() {
    return _baseConfigTemplate.getSleepTime();
  }

  public String getThrottleValue() {
    return _baseConfigTemplate.getThrottleValue();
  }

  private JPanel _footerButtons;
  private JButton _resetBtn;
  private JButton _saveBtn;

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

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("DefaultConfigTab");
    DefaultConfigTab debugView = new DefaultConfigTab();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
