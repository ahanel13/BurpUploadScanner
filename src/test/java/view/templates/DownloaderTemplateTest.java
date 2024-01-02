package view.templates;

import javax.swing.*;
import java.io.IOException;

public class DownloaderTemplateTest {
  public static void main(String[] args) throws IOException {
    JFrame               frame     = new JFrame("BaseConfigTemplate");
    DownloaderTemplate debugView = new DownloaderTemplate();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}