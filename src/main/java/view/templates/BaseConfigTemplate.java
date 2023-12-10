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
    _uniformCompSize = new Dimension(200, 50);

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
    component.setPreferredSize(_uniformCompSize);
    component.setMinimumSize(_uniformCompSize);
    component.setMaximumSize(_uniformCompSize);
  }

  public String getThrottleValue() {
    return _throttleValue.getText();
  }

  public JCheckBox getAddToLoggingChkBox() {
    return _addToLoggingChkBox;
  }

  public JCheckBox getReplaceFileName() {
    return _replaceFileName;
  }

  public JCheckBox getReplaceContentType() {
    return _replaceContentType;
  }

  public JCheckBox getReplaceFileSize() {
    return _replaceFileSize;
  }

  public JCheckBox getWgetCurlPayloads() {
    return _wgetCurlPayloads;
  }

  public String getSleepTime() {
    return _sleepTime.getText();
  }

  public void addSaveButtonListener(ActionListener listener) {
    _saveBtn.addActionListener(listener);
  }

  public void addResetButtonListener(ActionListener listener) {
    _resetBtn.addActionListener(listener);
  }

  public void setReplaceContentType(boolean replaceContentType) {
    _replaceContentType.setSelected(replaceContentType);
  }

  public void setReplaceFileName(boolean replaceFileName) {
    _replaceFileName.setSelected(replaceFileName);
  }

  public void setReplaceFileSize(boolean replaceFileSize) {
    _replaceFileSize.setSelected(replaceFileSize);
  }

  public void setAddToLoggingChkBox(boolean addToLogging) {
    _addToLoggingChkBox.setSelected(addToLogging);
  }

  public void setWgetCurlPayloads(boolean wgetCurlPayloads) {
    _wgetCurlPayloads.setSelected(wgetCurlPayloads);
  }

  public void setSleepTime(short sleepTime) {
    _sleepTime.setText(String.valueOf(sleepTime));
  }

  public void setThrottleValue(short throttleValue) {
    _throttleValue.setText(String.valueOf(throttleValue));
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
  private JLabel  _throttleTimeLabel;
  private JPanel  _scanningOptions;
  private JPanel  _payloadOptions;
  private JPanel  _generalOptions;
  private JLabel  _sleepTimeLabel;
  private JPanel  _footerButtons;
  private JPanel  _configGroups;
  private JButton _resetBtn;
  private JButton _saveBtn;

  // UI inputs
  private JCheckBox  _addToLoggingChkBox;
  private JCheckBox  _replaceContentType;
  private JCheckBox  _wgetCurlPayloads;
  private JCheckBox  _replaceFileName;
  private JCheckBox  _replaceFileSize;
  private JTextField _throttleValue;
  private JTextField _sleepTime;

  private final Properties _properties;
  private final Dimension  _uniformCompSize;

  private void addPayloadOptions() {
    _payloadOptions.setLayout(new BorderLayout(0, 0));
    _configGroups.add(_payloadOptions);

    _wgetCurlPayloads = new JCheckBox();
    _wgetCurlPayloads.setHorizontalTextPosition(10);
    _wgetCurlPayloads.setText(_properties.getProperty(WGET_CURL_LABEL));
    _wgetCurlPayloads.setMnemonic(' ');
    _wgetCurlPayloads.setDisplayedMnemonicIndex(10);
    _payloadOptions.add(_wgetCurlPayloads, BorderLayout.CENTER);
  }

  private void addScanningOptions() {
    _scanningOptions.setLayout(new BoxLayout(_scanningOptions, BoxLayout.Y_AXIS));
    _configGroups.add(_scanningOptions);

    _replaceFileSize = new JCheckBox();
    _replaceFileSize.setHorizontalTextPosition(10);
    _replaceFileSize.setSelected(true);
    _replaceFileSize.setText(_properties.getProperty(REPLACE_FILE_SIZE));
    _scanningOptions.add(_replaceFileSize);

    _replaceFileName = new JCheckBox();
    _replaceFileName.setHorizontalTextPosition(10);
    _replaceFileName.setSelected(true);
    _replaceFileName.setText(_properties.getProperty(REPLACE_FILE_NAME));
    _scanningOptions.add(_replaceFileName);

    _replaceContentType = new JCheckBox();
    _replaceContentType.setHorizontalTextPosition(10);
    _replaceContentType.setSelected(true);
    _replaceContentType.setText(_properties.getProperty(REPLACE_CONTENT_TYPE));
    _scanningOptions.add(_replaceContentType);
  }

  private void addGeneralOptions() {
    _generalOptions = new JPanel();
    _generalOptions.setLayout(new BorderLayout(0, 0));
    _configGroups.add(_generalOptions);

    _addToLoggingChkBox = new JCheckBox();
    _addToLoggingChkBox.setHorizontalTextPosition(10);
    _addToLoggingChkBox.setSelected(true);
    _addToLoggingChkBox.setText(_properties.getProperty(ADD_TO_LOGGING));
    _generalOptions.add(_addToLoggingChkBox, BorderLayout.CENTER);
  }

  private void addThrottleTimeGroup() {
    final JPanel throttleTimeGroup = new JPanel();
    throttleTimeGroup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    throttleTimeGroup.setMinimumSize(new Dimension(250, 40));
    scanningOptions.add(throttleTimeGroup);

    _throttleTimeLabel = new JLabel();
    _throttleTimeLabel.setText(_properties.getProperty(THROTTLE_TIME));
    throttleTimeGroup.add(_throttleTimeLabel);

    throttleValue = new JTextField();
    throttleTimeGroup.add(throttleValue);
  }

  private void addSleepOptions() {
    final JPanel sleepOptions = new JPanel();
    sleepOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    payloadOptions.add(sleepOptions, BorderLayout.WEST);

    _sleepTimeLabel = new JLabel();
    _sleepTimeLabel.setText(_properties.getProperty(SLEEP_TIME_LABEL));
    sleepOptions.add(_sleepTimeLabel);

    sleepTime = new JTextField();
    sleepOptions.add(sleepTime);
  }

  private void addFooterUi() {
    _footerButtons = new JPanel();
    _footerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    add(_footerButtons, BorderLayout.SOUTH);

    _resetBtn = new JButton();
    _resetBtn.setText("Reset");
    _footerButtons.add(_resetBtn);

    _saveBtn = new JButton();
    _saveBtn.setText("Save");
    _footerButtons.add(_saveBtn);
    _throttleTimeLabel.setLabelFor(_throttleValue);
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
