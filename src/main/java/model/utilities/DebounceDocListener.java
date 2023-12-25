package model.utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;

public class DebounceDocListener implements DocumentListener {
  private final Timer debounceTimer;
  
  public DebounceDocListener(int delay, ActionListener actionListener) {
    this.debounceTimer = new Timer(delay, actionListener);
    debounceTimer.setRepeats(false); // Ensure the timer only fires once after the last event
  }
  
  @Override
  public void insertUpdate(DocumentEvent e) {
    handleDocumentEvent();
  }
  
  @Override
  public void removeUpdate(DocumentEvent e) {
    handleDocumentEvent();
  }
  
  @Override
  public void changedUpdate(DocumentEvent e) {
    handleDocumentEvent();
  }
  
  private void handleDocumentEvent() {
    if (debounceTimer.isRunning()) {
      debounceTimer.restart();
    } else {
      debounceTimer.start();
    }
  }
}