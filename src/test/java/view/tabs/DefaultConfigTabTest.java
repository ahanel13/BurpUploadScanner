package view.tabs;

import javax.swing.*;
import java.io.IOException;

public class DefaultConfigTabTest{
  public static void main(String[] args) throws IOException{
    JFrame           frame     = new JFrame("DefaultConfigTab");
    DefaultConfigTab debugView = new DefaultConfigTab();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}