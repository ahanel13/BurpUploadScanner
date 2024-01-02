package view.templates;

import javax.swing.*;
import java.awt.*;

public class DownloaderTemplate extends JPanel {
  ////////////////////////////////////////
  // PUBLIC FIELDS
  ////////////////////////////////////////
  public final JTextField preflightEndpointInput;
  public final JTextField startMarker;
  public final JTextField endMarker;
  public final JCheckBox  replaceBackslash;
  public final JTextField prefix;
  public final JTextField suffix;
  public final JTextField staticUrl;

  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public DownloaderTemplate() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    Dimension mainSize = new Dimension(750, 275);
    setPreferredSize(mainSize);
    setMaximumSize(mainSize);
    setMinimumSize(mainSize);

    preflightEndpointInput = new JTextField();
    startMarker            = new JTextField();
    endMarker              = new JTextField();
    replaceBackslash       = new JCheckBox();
    prefix                 = new JTextField();
    suffix                 = new JTextField();
    staticUrl              = new JTextField();

    add(_getSectionTitle());
    add(_getPreflightSection());
    add(_getTxtInputGroup(startMarker, START_MARKER_TXT));
    add(_getTxtInputGroup(endMarker, END_MARKER_TXT));
    add(_getReplaceBackslash());
    add(_getTxtInputGroup(prefix, PREFIX_TXT));
    add(_getTxtInputGroup(suffix, SUFFIX_TXT));
    add(_getTxtInputGroup(staticUrl, STATIC_URL_TXT));
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private static final Dimension STD_DIMENSION         = new Dimension(750, 50);
  private static final String    SECTION_TITLE         = "Downloader parser options";
  private static final String    PREFLIGHT_CHKBOX_TXT  =
      "Send a preflight request before searching for the download endpoint";
  private static final String    PREFLIGHT_INPUT_TXT   = "Enter preflight endpoint eg. http://example.com/myprofile/";
  private static final String    START_MARKER_TXT      =
      "Start marker to parse URL from response, eg. MARKER/upload/file.png";
  private static final String    END_MARKER_TXT        =
      "End marker to parse URL from response, eg. /upload/file.pngMARKER";
  private static final String    REPLACE_BACKSLASH_TXT = "Replace \\/ with / in parsed content";
  private static final String    PREFIX_TXT            = "Add a prefix to the parsed URL (you can use ${FILENAME})";
  private static final String    SUFFIX_TXT            = "Add a suffix to the parsed URL (you can use ${FILENAME})";
  private static final String    STATIC_URL_TXT        =
      "Alternatively, a static URL, eg. http://example.com/upload/${FILENAME}";

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private static JPanel _setUpChkBoxGroup(JCheckBox checkBox, String txt) {
    JPanel inputGrp = new JPanel();
    checkBox.setSelected(false);
    checkBox.setText(txt);
    checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
    inputGrp.add(checkBox);
    Dimension dimension = new Dimension(500, 25);
    inputGrp.setMaximumSize(dimension);
    inputGrp.setMinimumSize(dimension);
    inputGrp.setPreferredSize(dimension);
    return inputGrp;
  }

  private static JPanel _getTxtInputGroup(JTextField input, String txt) {
    JPanel inputGrp = new JPanel();
    inputGrp.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

    JLabel txtLabel = new JLabel(txt);
    input.setColumns(20);
    inputGrp.add(txtLabel);
    inputGrp.add(input);
    inputGrp.setMaximumSize(STD_DIMENSION);
    return inputGrp;
  }

  private JPanel _getSectionTitle() {
    JLabel title   = new JLabel(SECTION_TITLE);
    JPanel section = new JPanel();
    section.add(title);
    return section;
  }

  private JPanel _getPreflightSection() {
    JPanel grp = _getTxtInputGroup(preflightEndpointInput, PREFLIGHT_INPUT_TXT);

    JPanel section = new JPanel();
    section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));

    section.add(grp);
    return section;
  }

  private JPanel _getReplaceBackslash() {
    JPanel chkBoxGroup = _setUpChkBoxGroup(replaceBackslash, REPLACE_BACKSLASH_TXT);
    replaceBackslash.setSelected(true);

    JPanel section = new JPanel();
    section.add(chkBoxGroup);
    return section;
  }
}
