package view.templates;

import model.utilities.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

//todo: get rid of magic strings
public class BaseConfigTemplate extends JPanel {

//  private final Object _constraints;

  public BaseConfigTemplate() throws IOException {
    super(); // Call the parent class constructor without BoxLayout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set BoxLayout here
    setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    setMaximumSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));

    initializeUIComponents();
    _properties          = ResourceLoader.loadPropertyFile(BASE_CONFIG_TEMPLATE_PROPERTIES);
//    _constraints         = getComponentConstraints();
    uniformComponentSize = new Dimension(200, 50);

    // Config Groups
    configGroups = new JPanel();
    configGroups.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    configGroups.setMaximumSize(new Dimension(250, 32767));
    add(configGroups, BorderLayout.CENTER);

    // Config Groups: Scanning Options
    addScanningOptions();
    addThrottleTimeGroup();
    addPayloadOptions();
    addSleepOptions();
    addGeneralOptions();
    addFooterUi();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
  }

  @Override
  public Component add(Component comp) {
    addImpl(comp, null, -1);
    return  comp; // why does this function return the given argument?
  }

  private void getUniformSize(Component component) {
    component.setPreferredSize(uniformComponentSize);
    component.setMinimumSize(uniformComponentSize);
    component.setMaximumSize(uniformComponentSize);
  }

  public String getThrottleValue() {
    return throttleValue.getText();
  }

  public JCheckBox getAddToLoggingChkBox() {
    return addToLoggingChkBox;
  }

  public JCheckBox getReplaceFileName() {
    return replaceFileName;
  }

  public JCheckBox getReplaceContentType() {
    return replaceContentType;
  }

  public JCheckBox getReplaceFileSize() {
    return replaceFileSize;
  }

  public JCheckBox getWgetCurlPayloads() {
    return wgetCurlPayloads;
  }

  public String getSleepTime() {
    return sleepTime.getText();
  }

  public void addSaveButtonListener(ActionListener listener) {
    saveBtn.addActionListener(listener);
  }

  public void addResetButtonListener(ActionListener listener) {
    resetBtn.addActionListener(listener);
  }

  public void setReplaceContentType(boolean replaceContentType) {
    this.replaceContentType.setSelected(replaceContentType);
  }

  public void setReplaceFileName(boolean replaceFileName) {
    this.replaceFileName.setSelected(replaceFileName);
  }

  public void setReplaceFileSize(boolean replaceFileSize) {
    this.replaceFileSize.setSelected(replaceFileSize);
  }

  public void setAddToLoggingChkBox(boolean addToLogging) {
    this.addToLoggingChkBox.setSelected(addToLogging);
  }

  public void setWgetCurlPayloads(boolean wgetCurlPayloads) {
    this.wgetCurlPayloads.setSelected(wgetCurlPayloads);
  }

  public void setSleepTime(short sleepTime) {
    this.sleepTime.setText(String.valueOf(sleepTime));
  }

  public void setThrottleValue(short throttleValue) {
    this.throttleValue.setText(String.valueOf(throttleValue));
  }

  // CONSTANTS
  private static final int MIN_WIDTH        = 250;
  private static final int MIN_HEIGHT       = 500;
  private static final int MAX_WIDTH        = 1000;
  private static final int MAX_HEIGHT       = 1000;
  private static final int PREFERRED_WIDTH  = 500;
  private static final int PREFERRED_HEIGHT = 500;

  private static final String BASE_CONFIG_TEMPLATE_PROPERTIES = "BaseConfigView.properties";
  private static final String WGET_CURL_LABEL                 = "wget.curl.label";
  private static final String REPLACE_FILE_NAME               = "replace.file.name";
  private static final String REPLACE_FILE_SIZE               = "replace.file.size";
  private static final String REPLACE_CONTENT_TYPE            = "replace.content.type";
  private static final String ADD_TO_LOGGING                  = "add.to.logging.tab";
  private static final String THROTTLE_TIME                   = "throttle.time";
  private static final String SLEEP_TIME_LABEL                = "sleep.time.label";

  // UI components
  private JLabel  throttleTimeLabel;
  private JPanel  scanningOptions;
  private JPanel  payloadOptions;
  private JPanel  generalOptions;
  private JLabel  sleepTimeLabel;
  private JPanel  footerButtons;
  private JPanel  configGroups;
  private JButton resetBtn;
  private JButton saveBtn;

  // UI inputs
  private JCheckBox  addToLoggingChkBox;
  private JCheckBox  replaceContentType;
  private JCheckBox  wgetCurlPayloads;
  private JCheckBox  replaceFileName;
  private JCheckBox  replaceFileSize;
  private JTextField throttleValue;
  private JTextField sleepTime;

  private final Properties _properties;
  private final Dimension uniformComponentSize;

  private void addPayloadOptions() {
    payloadOptions.setLayout(new BorderLayout(0, 0));
    configGroups.add(payloadOptions);

    wgetCurlPayloads = new JCheckBox();
    wgetCurlPayloads.setHorizontalTextPosition(10);
    wgetCurlPayloads.setText(_properties.getProperty(WGET_CURL_LABEL));
    wgetCurlPayloads.setMnemonic(' ');
    wgetCurlPayloads.setDisplayedMnemonicIndex(10);
    payloadOptions.add(wgetCurlPayloads, BorderLayout.CENTER);
  }

  private void addScanningOptions() {
    scanningOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    configGroups.add(scanningOptions);

    replaceFileSize = new JCheckBox();
    replaceFileSize.setHorizontalTextPosition(10);
    replaceFileSize.setSelected(true);
    replaceFileSize.setText(_properties.getProperty(REPLACE_FILE_SIZE));
    scanningOptions.add(replaceFileSize);

    replaceFileName = new JCheckBox();
    replaceFileName.setHorizontalTextPosition(10);
    replaceFileName.setSelected(true);
    replaceFileName.setText(_properties.getProperty(REPLACE_FILE_NAME));
    scanningOptions.add(replaceFileName);

    replaceContentType = new JCheckBox();
    replaceContentType.setHorizontalTextPosition(10);
    replaceContentType.setSelected(true);
    replaceContentType.setText(_properties.getProperty(REPLACE_CONTENT_TYPE));
    scanningOptions.add(replaceContentType);
  }

  private void addGeneralOptions() {
    generalOptions = new JPanel();
    generalOptions.setLayout(new BorderLayout(0, 0));
    configGroups.add(generalOptions);

    addToLoggingChkBox = new JCheckBox();
    addToLoggingChkBox.setHorizontalTextPosition(10);
    addToLoggingChkBox.setSelected(true);
    addToLoggingChkBox.setText(_properties.getProperty(ADD_TO_LOGGING));
    generalOptions.add(addToLoggingChkBox, BorderLayout.CENTER);
  }

  private void addThrottleTimeGroup() {
    final JPanel throttleTimeGroup = new JPanel();
    throttleTimeGroup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    throttleTimeGroup.setMinimumSize(new Dimension(250, 40));
    scanningOptions.add(throttleTimeGroup);

    throttleTimeLabel = new JLabel();
    throttleTimeLabel.setText(_properties.getProperty(THROTTLE_TIME));
    throttleTimeGroup.add(throttleTimeLabel);

    throttleValue = new JTextField();
    throttleTimeGroup.add(throttleValue);
  }

  private void addSleepOptions() {
    final JPanel sleepOptions = new JPanel();
    sleepOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    payloadOptions.add(sleepOptions, BorderLayout.WEST);

    sleepTimeLabel = new JLabel();
    sleepTimeLabel.setText(_properties.getProperty(SLEEP_TIME_LABEL));
    sleepOptions.add(sleepTimeLabel);

    sleepTime = new JTextField();
    sleepOptions.add(sleepTime);
  }

  private void addFooterUi() {
    footerButtons = new JPanel();
    footerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    add(footerButtons, BorderLayout.SOUTH);

    resetBtn = new JButton();
    resetBtn.setText("Reset");
    footerButtons.add(resetBtn);

    saveBtn = new JButton();
    saveBtn.setText("Save");
    footerButtons.add(saveBtn);
    throttleTimeLabel.setLabelFor(throttleValue);
  }

  private void initializeUIComponents() {
    configGroups    = new JPanel();
    scanningOptions = new JPanel();
    payloadOptions  = new JPanel();
    generalOptions  = new JPanel();
    footerButtons   = new JPanel();
  }

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("BaseConfigTemplate");
    frame.setContentPane(new BaseConfigTemplate());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
