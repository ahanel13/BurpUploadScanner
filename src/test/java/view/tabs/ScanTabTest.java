package view.tabs;

import javax.swing.*;
import java.io.IOException;

public class ScanTabTest{
  public static void main(String[] args) throws IOException{
    JFrame           frame     = new JFrame("DefaultConfigTab");
    ScanTab debugView = new ScanTab(null, null);
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}