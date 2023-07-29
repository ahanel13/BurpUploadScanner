package view.tabs.about;

import burp.api.montoya.MontoyaApi;
import view.tabs.TabFactory;

import javax.swing.*;

public class AboutTabFactory extends TabFactory {

  public AboutTabFactory(String tabName, MontoyaApi api) {
    super(tabName, api);
  }

  private void createUIComponents(JPanel panel) {
    JLabel aboutText = new JLabel();
    aboutText.setText("""
        <html>
        <h1>Upload Scanner</h1>

        <h2>Description</h2>
        <hr>
        A Burp Suite Pro extension to do security tests for HTTP file uploads.<br>
        For more information see https://github.com/modzero/mod0BurpUploadScanner/<br>

        <h2>Contributions</h2>
        <hr>
        <b>Author: Anthony Hanel, @ahanel13, https://anthonyhanel.me</b><br><br>
        Based on the <a href="https://github.com/modzero/mod0BurpUploadScanner">Python Upload Scanner</a> written by:
        <ul>
            <li>Tobias "floyd" Ospelt, @floyd_ch, https://www.floyd.ch</li>
            <li>modzero AG, @mod0, https://www.modzero.ch</li>
        </ul>
        </html>""");
    aboutText.putClientProperty("html.disable", null);
    panel.add(aboutText);
  }

  @Override
  public JPanel createTab() {
    JPanel panel = new JPanel();
    createUIComponents(panel);
    return panel;
  }
}
