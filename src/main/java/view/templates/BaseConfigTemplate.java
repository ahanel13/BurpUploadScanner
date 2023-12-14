package view.templates;

import model.utilities.ResourceLoader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

//todo: get rid of magic strings
public class BaseConfigTemplate extends JPanel {

  public BaseConfigTemplate() throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new EmptyBorder(20,20,20,20));

    initializeUIComponents();
    _properties = ResourceLoader.loadPropertyFile(BASE_CONFIG_TEMPLATE_PROPERTIES);

    addFileOptions();
    getThrottleTimeGroup();
    addPayloadOptions();
    addSleepOptions();
    addGeneralOptions();
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

  ///////////////////////////////////////
  // CONSTANTS
  ///////////////////////////////////////
  private static final Dimension STD_INPUT_SIZE = new Dimension(200, 30);
  private static final int       STD_JTEXT_COLS = 3;
  private static final Border    STD_BORDER     = new EmptyBorder(5, 5, 5, 5);


  // Keys
  private static final String BASE_CONFIG_TEMPLATE_PROPERTIES = "BaseConfigView.properties";
  private static final String WGET_CURL_LABEL                 = "wget.curl.label";
  private static final String REPLACE_FILE_NAME               = "replace.file.name";
  private static final String REPLACE_FILE_SIZE               = "replace.file.size";
  private static final String REPLACE_CONTENT_TYPE            = "replace.content.type";
  private static final String ADD_TO_LOGGING                  = "add.to.logging.tab";
  private static final String THROTTLE_TIME                   = "throttle.time";
  private static final String SLEEP_TIME_LABEL                = "sleep.time.label";

  private final Properties _properties;

  // UI components
  private JPanel _generalOptions;
  private JPanel _fileOptions;
  private JPanel _payloadOptions;

  // Scanning Config Components
  private JLabel _throttleTimeLabel;
  private JLabel _sleepTimeLabel;

  // UI inputs
  private JCheckBox  _addToLoggingChkBox;
  private JCheckBox  _replaceContentType;
  private JCheckBox  _wgetCurlPayloads;
  private JCheckBox  _replaceFileName;
  private JCheckBox  _replaceFileSize;
  private JTextField _throttleValue;
  private JTextField _sleepTime;


  private void addPayloadOptions() {
    _payloadOptions.setLayout(new BoxLayout(_payloadOptions, BoxLayout.Y_AXIS));
    _payloadOptions.setBorder(STD_BORDER);
    _payloadOptions.setAlignmentX(CENTER_ALIGNMENT);
    _payloadOptions.add(getWgetCurlOpt());
    add(_payloadOptions);
  }

  private JPanel getWgetCurlOpt(){
    _wgetCurlPayloads = new JCheckBox();
    _wgetCurlPayloads.setHorizontalTextPosition(10);
    _wgetCurlPayloads.setText(_properties.getProperty(WGET_CURL_LABEL));

      JPanel opts = getCheckBoxBorderJPanel();
      opts.add(_wgetCurlPayloads, BorderLayout.EAST);
      return  opts;
  }

  private void addFileOptions() {
    _fileOptions.setLayout(new BoxLayout(_fileOptions, BoxLayout.Y_AXIS));
    _fileOptions.setBorder(STD_BORDER);
    _fileOptions.add(getReplaceFileSizeOpt());
    _fileOptions.add(getReplaceFileNameOpt());
    _fileOptions.add(getReplaceFileContentOpt());
    add(_fileOptions);
  }

  private JPanel getReplaceFileSizeOpt(){
    _replaceFileSize = new JCheckBox();
    _replaceFileSize.setHorizontalTextPosition(10);
    _replaceFileSize.setSelected(true);
    _replaceFileSize.setText(_properties.getProperty(REPLACE_FILE_SIZE));

    JPanel fileSizeOpt = getCheckBoxBorderJPanel();
    fileSizeOpt.add(_replaceFileSize, BorderLayout.EAST);
    return fileSizeOpt;
  }

  private JPanel getReplaceFileNameOpt() {
    _replaceFileName = new JCheckBox();
    _replaceFileName.setHorizontalTextPosition(10);
    _replaceFileName.setSelected(true);
    _replaceFileName.setText(_properties.getProperty(REPLACE_FILE_NAME));

    JPanel replaceFileNameOpt = getCheckBoxBorderJPanel();
    replaceFileNameOpt.add(_replaceFileName, BorderLayout.EAST);

    return replaceFileNameOpt;
  }

  private JPanel getReplaceFileContentOpt() {
    _replaceContentType = new JCheckBox();
    _replaceContentType.setHorizontalTextPosition(10);
    _replaceContentType.setSelected(true);
    _replaceContentType.setText(_properties.getProperty(REPLACE_CONTENT_TYPE));

    JPanel replaceFileContOpt = getCheckBoxBorderJPanel();
    replaceFileContOpt.add(_replaceContentType, BorderLayout.EAST);

    return replaceFileContOpt;
  }

  private JPanel getCheckBoxBorderJPanel(){
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new BorderLayout());
    tempPanel.setAlignmentX(CENTER_ALIGNMENT);
    tempPanel.setMaximumSize(STD_INPUT_SIZE);
    return tempPanel;
  }

  private void addGeneralOptions() {
    _generalOptions.setLayout(new BoxLayout(_generalOptions, BoxLayout.Y_AXIS));
    _generalOptions.setBorder(STD_BORDER);
    _generalOptions.add(getAddToLoggingOpt());
    _generalOptions.add(getThrottleTimeGroup());
    add(_generalOptions);
  }

  private JPanel getAddToLoggingOpt(){
      _addToLoggingChkBox = new JCheckBox();
      _addToLoggingChkBox.setHorizontalTextPosition(10);
      _addToLoggingChkBox.setSelected(true);
      _addToLoggingChkBox.setText(_properties.getProperty(ADD_TO_LOGGING));

      JPanel opt = getCheckBoxBorderJPanel();
      opt.add(_addToLoggingChkBox);
      return opt;
  }

  private JPanel getTextFileGroupPanel(){
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    tempPanel.setMaximumSize(STD_INPUT_SIZE);
    tempPanel.setAlignmentX(CENTER_ALIGNMENT);
    return tempPanel;
  }

  private JPanel getThrottleTimeGroup() {
    // Panel Group
    final JPanel throttleTimeGroup = getTextFileGroupPanel();

    _throttleTimeLabel = new JLabel();
    _throttleTimeLabel.setText(_properties.getProperty(THROTTLE_TIME));
    throttleTimeGroup.add(_throttleTimeLabel);

    _throttleValue = new JTextField(STD_JTEXT_COLS);
    throttleTimeGroup.add(_throttleValue);

    return throttleTimeGroup;
  }

  private void addSleepOptions() {
    final JPanel sleepOptions = getTextFileGroupPanel();

    _sleepTimeLabel = new JLabel();
    _sleepTimeLabel.setText(_properties.getProperty(SLEEP_TIME_LABEL));
    sleepOptions.add(_sleepTimeLabel);

    _sleepTime = new JTextField(STD_JTEXT_COLS);
    sleepOptions.add(_sleepTime);

    _payloadOptions.add(sleepOptions);
  }

  private void initializeUIComponents() {
    _fileOptions    = new JPanel();
    _payloadOptions = new JPanel();
    _generalOptions = new JPanel();
  }

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("BaseConfigTemplate");
    BaseConfigTemplate debugView = new BaseConfigTemplate();
//    debugView.addColors();
    frame.setContentPane(debugView);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  private void addColors() {
    // Color constants
    final Color FILE_OPTIONS_COLOR    = new Color(110, 165, 117);
    final Color PAYLOAD_OPTIONS_COLOR = new Color(110, 117, 165);
    final Color GENERAL_OPTIONS_COLOR = new Color(165, 110, 149);
    final Color BACKGROUND_COLOR      = new Color(165, 110, 110);

    _fileOptions.setBackground(FILE_OPTIONS_COLOR);
    _payloadOptions.setBackground(PAYLOAD_OPTIONS_COLOR);
    _generalOptions.setBackground(GENERAL_OPTIONS_COLOR);
    setBackground(BACKGROUND_COLOR);
  }
}
