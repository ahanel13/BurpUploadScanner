package view.tabs;

import model.utilities.ResourceLoader;

import javax.swing.*;

public class AboutTab extends JPanel {
    private final String      tabName;
    private final JEditorPane aboutText;

    public AboutTab() throws Exception {
        tabName   = "About";
        aboutText = getAboutText();
        add(aboutText);
    }

    private JEditorPane getAboutText() throws Exception {
        String      htmlContent = ResourceLoader.loadResource("about.html");
        JEditorPane aboutText   = new JEditorPane("text/html", htmlContent);

        aboutText.setEditable(false);
        return aboutText;
    }

    public String getTabName() {
        return tabName;
    }
}
