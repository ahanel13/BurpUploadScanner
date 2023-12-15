package view.templates;

import javax.swing.*;
import java.io.IOException;


public class BaseConfigTemplateTest{
  public static void main(String[] args) throws IOException{
    JFrame             frame     = new JFrame("BaseConfigTemplate");
    BaseConfigTemplate debugView = new BaseConfigTemplate();
    debugView.addColors();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}