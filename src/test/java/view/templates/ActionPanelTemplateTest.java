package view.templates;

import javax.swing.*;
import java.io.IOException;


public class ActionPanelTemplateTest {
  public static void main(String[] args) throws IOException {
    JFrame              frame     = new JFrame("BaseConfigTemplate");
    ActionPanelTemplate debugView = new ActionPanelTemplate();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}