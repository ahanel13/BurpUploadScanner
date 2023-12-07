package view.tabs;

import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DefaultConfigTab extends JPanel {

  public DefaultConfigTab() throws IOException {
    _baseConfigTemplate = new BaseConfigTemplate();
    add(_baseConfigTemplate);
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

  public void addResetButtonListener(ActionListener actionListener) {
    _baseConfigTemplate.addResetButtonListener(actionListener);
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

  public void addSaveButtonListener(ActionListener actionListener) {
    _baseConfigTemplate.addSaveButtonListener(actionListener);
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
}
