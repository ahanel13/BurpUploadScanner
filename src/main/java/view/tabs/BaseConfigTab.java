package view.tabs;

import view.templates.BaseConfigTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseConfigTab extends JPanel {
  public static final String TAB_NAME = "Global Config";

  public BaseConfigTab() throws IOException {
    setLayout(new BorderLayout());
    _baseConfigTemplate = new BaseConfigTemplate();
    JScrollPane pane = new JScrollPane(_baseConfigTemplate);
    pane.getVerticalScrollBar().setUnitIncrement(20);
    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    add(pane);
    addFooterUi();
  }

// PUBLIC METHODS //
  //GETTERS
  public AbstractButton getReplaceContentType() { return _baseConfigTemplate.getReplaceContentType(); }
  public AbstractButton getReplaceFileName()    { return _baseConfigTemplate.getReplaceFileName(); }
  public AbstractButton getReplaceFileSize()    { return _baseConfigTemplate.getReplaceFileSize(); }
  public String         getSleepTime()          { return _baseConfigTemplate.getSleepTime(); }
  public AbstractButton getWgetCurlPayloads()   { return _baseConfigTemplate.getWgetCurlPayloads(); }
  public String         getThrottleValue()      { return _baseConfigTemplate.getThrottleValue(); }
  public AbstractButton getAddToLoggingChkBox() { return _baseConfigTemplate.getAddToLoggingChkBox(); }
  
  // SETTERS
  public void setReplaceFileName(boolean b)            { _baseConfigTemplate.setReplaceFileName(b);}
  public void setReplaceFileSize(boolean b)            { _baseConfigTemplate.setReplaceFileSize(b);}
  public void setAddToLoggingChkBox(boolean b)         { _baseConfigTemplate.setReplaceContentType(b);}
  public void setWgetCurlPayloads(boolean b)           { _baseConfigTemplate.setWgetCurlPayloads(b);}
  public void setSleepTime(short i)                    { _baseConfigTemplate.setSleepTime(i);}
  public void setThrottleValue(short i)                { _baseConfigTemplate.setThrottleValue(i);}
  public void setReplaceContentType(boolean b)         { _baseConfigTemplate.setAddToLoggingChkBox(b);}
  public void setGifFileType(boolean b)                { _baseConfigTemplate.setGifFileType(b);}
  public void setPngFileType(boolean b)                { _baseConfigTemplate.setPngFileType(b);}
  public void setJpegFileType(boolean b)               { _baseConfigTemplate.setJpegFileType(b);}
  public void setTiffFileType(boolean b)               { _baseConfigTemplate.setTiffFileType(b);}
  public void setIcoFileType(boolean b)                { _baseConfigTemplate.setIcoFileType(b);}
  public void setSvgFileType(boolean b)                { _baseConfigTemplate.setSvgFileType(b);}
  public void setMvgFileType(boolean b)                { _baseConfigTemplate.setMvgFileType(b);}
  public void setPdfFileType(boolean b)                { _baseConfigTemplate.setPdfFileType(b);}
  public void setMp4FileType(boolean b)                { _baseConfigTemplate.setMp4FileType(b);}
  public void setDocxFileType(boolean b)               { _baseConfigTemplate.setDocxFileType(b);}
  public void setXlsxFileType(boolean b)               { _baseConfigTemplate.setXlsxFileType(b);}
  public void setSwfFileType(boolean b)                { _baseConfigTemplate.setSwfFileType(b);}
  public void setCsvFileType(boolean b)                { _baseConfigTemplate.setCsvFileType(b);}
  public void setZipFileType(boolean b)                { _baseConfigTemplate.setZipFileType(b);}
  public void setGzipFileType(boolean b)               { _baseConfigTemplate.setGzipFileType(b);}
  public void setHtmlFileType(boolean b)               { _baseConfigTemplate.setHtmlFileType(b);}
  public void setXmlFileType(boolean b)                { _baseConfigTemplate.setXmlFileType(b);}
  public void setActivescanScanCheck(boolean b)        { _baseConfigTemplate.setActivescanScanCheck(b);}
  public void setImagetragickScanCheck(boolean b)      { _baseConfigTemplate.setImagetragickScanCheck(b);}
  public void setMagickScanCheck(boolean b)            { _baseConfigTemplate.setMagickScanCheck(b);}
  public void setGsScanCheck(boolean b)                { _baseConfigTemplate.setGsScanCheck(b);}
  public void setLibavformatScanCheck(boolean b)       { _baseConfigTemplate.setLibavformatScanCheck(b);}
  public void setPhpScanCheck(boolean b)               { _baseConfigTemplate.setPhpScanCheck(b);}
  public void setJspScanCheck(boolean b)               { _baseConfigTemplate.setJspScanCheck(b);}
  public void setAspScanCheck(boolean b)               { _baseConfigTemplate.setAspScanCheck(b);}
  public void setHtaccessScanCheck(boolean b)          { _baseConfigTemplate.setHtaccessScanCheck(b);}
  public void setCgiScanCheck(boolean b)               { _baseConfigTemplate.setCgiScanCheck(b);}
  public void setSsiScanCheck(boolean b)               { _baseConfigTemplate.setSsiScanCheck(b);}
  public void setXxeScanCheck(boolean b)               { _baseConfigTemplate.setXxeScanCheck(b);}
  public void setXssScanCheck(boolean b)               { _baseConfigTemplate.setXssScanCheck(b);}
  public void setEicarScanCheck(boolean b)             { _baseConfigTemplate.setEicarScanCheck(b);}
  public void setPdfInjectionScanCheck(boolean b)      { _baseConfigTemplate.setPdfInjectionScanCheck(b);}
  public void setSsrfScanCheck(boolean b)              { _baseConfigTemplate.setSsrfScanCheck(b);}
  public void setCsvInjectionScanCheck(boolean b)      { _baseConfigTemplate.setCsvInjectionScanCheck(b);}
  public void setPathTraversalScanCheck(boolean b)     { _baseConfigTemplate.setPathTraversalScanCheck(b);}
  public void setPolyglotScanCheck(boolean b)          { _baseConfigTemplate.setPolyglotScanCheck(b);}
  public void setFingerpingScanCheck(boolean b)        { _baseConfigTemplate.setFingerpingScanCheck(b);}
  public void setQuirksScanCheck(boolean b)            { _baseConfigTemplate.setQuirksScanCheck(b);}
  public void setUrlReplacerScanCheck(boolean b)       { _baseConfigTemplate.setUrlReplacerScanCheck(b);}
  public void setRecursiveUploaderScanCheck(boolean b) { _baseConfigTemplate.setRecursiveUploaderScanCheck(b);}
  public void setFuzzerScanCheck(boolean b)            { _baseConfigTemplate.setFuzzerScanCheck(b);}
  public void setDosScanCheck(boolean b)               { _baseConfigTemplate.setDosScanCheck(b);}
  
  
  public void addSaveButtonListener(ActionListener l)  { _saveBtn.addActionListener(l); }
  public void addResetButtonListener(ActionListener l) { _resetBtn.addActionListener(l); }
  
  private final BaseConfigTemplate _baseConfigTemplate;
  private       JPanel             _footerButtons;
  private       JButton            _resetBtn;
  private       JButton            _saveBtn;

  private void addFooterUi() {
    _footerButtons = new JPanel();
    _footerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

    _resetBtn = new JButton();
    _resetBtn.setText("Reset");
    _footerButtons.add(_resetBtn);

    _saveBtn = new JButton();
    _saveBtn.setText("Save");
    _footerButtons.add(_saveBtn);

    add(_footerButtons, BorderLayout.SOUTH);
  }
}
