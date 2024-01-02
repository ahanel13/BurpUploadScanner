package controller.tabControllers;

import model.BaseConfigModel;
import view.tabs.BaseConfigTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseConfigController {
  ////////////////////////////////////////
  // PUBLIC FIELDS
  ////////////////////////////////////////
  public String getTabName() {return BaseConfigTab.TAB_NAME;}
  public JPanel getTab()     {return view;}

  ////////////////////////////////////////
  // PUBLIC FUNCTIONS
  ////////////////////////////////////////
  public BaseConfigController(BaseConfigModel baseConfigModel, BaseConfigTab baseConfigTab) {
    model = baseConfigModel;
    view  = baseConfigTab;
    addSaveButtonListener();
    addResetButtonListener();
    updateView();
  }

  ////////////////////////////////////////
  // PRIVATE FIELDS
  ////////////////////////////////////////
  private final BaseConfigModel model;
  private final BaseConfigTab   view;

  ////////////////////////////////////////
  // PRIVATE METHODS
  ////////////////////////////////////////
  private void addSaveButtonListener() {
    view.addSaveButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // File Settings
        model.setReplaceContentType(view.getReplaceContentType().isSelected());
        model.setReplaceFileName(view.getReplaceFileName().isSelected());
        model.setReplaceFileSize(view.getReplaceFileSize().isSelected());
        model.setAddToLoggingTab(view.getAddToLoggingChkBox().isSelected());
        model.setWgetCurlPayloads(view.getWgetCurlPayloads().isSelected());
        model.setSleepTime(Short.parseShort(view.getSleepTime()));
        model.setThrottleTime(Short.parseShort(view.getThrottleValue()));

        // File Types
        model.setGifFileType(view.getGifFileType().isSelected());
        model.setPngFileType(view.getPngFileType().isSelected());
        model.setJpegFileType(view.getJpegFileType().isSelected());
        model.setTiffFileType(view.getTiffFileType().isSelected());
        model.setIcoFileType(view.getIcoFileType().isSelected());
        model.setSvgFileType(view.getSvgFileType().isSelected());
        model.setMvgFileType(view.getMvgFileType().isSelected());
        model.setPdfFileType(view.getPdfFileType().isSelected());
        model.setMp4FileType(view.getMp4FileType().isSelected());
        model.setDocxFileType(view.getDocxFileType().isSelected());
        model.setXlsxFileType(view.getXlsxFileType().isSelected());
        model.setSwfFileType(view.getSwfFileType().isSelected());
        model.setCsvFileType(view.getCsvFileType().isSelected());
        model.setZipFileType(view.getZipFileType().isSelected());
        model.setGzipFileType(view.getGzipFileType().isSelected());
        model.setHtmlFileType(view.getHtmlFileType().isSelected());
        model.setXmlFileType(view.getXmlFileType().isSelected());

        // Scan Checks
        model.setActivescanScanCheck(view.getActivescanScanCheck().isSelected());
        model.setImagetragickScanCheck(view.getImagetragickScanCheck().isSelected());
        model.setMagickScanCheck(view.getMagickScanCheck().isSelected());
        model.setGsScanCheck(view.getGsScanCheck().isSelected());
        model.setLibavformatScanCheck(view.getLibavformatScanCheck().isSelected());
        model.setPhpScanCheck(view.getPhpScanCheck().isSelected());
        model.setJspScanCheck(view.getJspScanCheck().isSelected());
        model.setAspScanCheck(view.getAspScanCheck().isSelected());
        model.setHtaccessScanCheck(view.getHtaccessScanCheck().isSelected());
        model.setCgiScanCheck(view.getCgiScanCheck().isSelected());
        model.setSsiScanCheck(view.getSsiScanCheck().isSelected());
        model.setXxeScanCheck(view.getXxeScanCheck().isSelected());
        model.setXssScanCheck(view.getXssScanCheck().isSelected());
        model.setEicarScanCheck(view.getEicarScanCheck().isSelected());
        model.setPdfInjectionScanCheck(view.getPdfInjectionScanCheck().isSelected());
        model.setSsrfScanCheck(view.getSsrfScanCheck().isSelected());
        model.setCsvInjectionScanCheck(view.getCsvInjectionScanCheck().isSelected());
        model.setPathTraversalScanCheck(view.getPathTraversalScanCheck().isSelected());
        model.setPolyglotScanCheck(view.getPolyglotScanCheck().isSelected());
        model.setFingerpingScanCheck(view.getFingerpingScanCheck().isSelected());
        model.setQuirksScanCheck(view.getQuirksScanCheck().isSelected());
        model.setUrlReplacerScanCheck(view.getUrlReplacerScanCheck().isSelected());
        model.setRecursiveUploader(view.getRecursiveUploaderScanCheck().isSelected());
        model.setFuzzerScanCheck(view.getFuzzerScanCheck().isSelected());
        model.setDosScanCheck(view.getDosScanCheck().isSelected());

        model.persist();

        //Todo: add error handling when a valid short cannot be cast.
      }
    });
  }

  private void addResetButtonListener() {
    view.addResetButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          model.reset();
        }
        catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        updateView();
      }
    });
  }

  private void updateView() {
    view.setReplaceFileName(model.replaceFileName());
    view.setReplaceFileSize(model.replaceFileSize());
    view.setReplaceContentType(model.replaceContentType());
    view.setAddToLoggingChkBox(model.addToLoggingTab());
    view.setWgetCurlPayloads(model.wgetCurlPayloads());
    view.setSleepTime(model.sleepTime());
    view.setThrottleValue(model.throttleTime());

    view.setGifFileType(model.gifFileType());
    view.setPngFileType(model.pngFileType());
    view.setJpegFileType(model.jpegFileType());
    view.setTiffFileType(model.tiffFileType());
    view.setIcoFileType(model.icoFileType());
    view.setSvgFileType(model.svgFileType());
    view.setMvgFileType(model.mvgFileType());
    view.setPdfFileType(model.pdfFileType());
    view.setMp4FileType(model.mp4FileType());
    view.setDocxFileType(model.docxFileType());
    view.setXlsxFileType(model.xlsxFileType());
    view.setSwfFileType(model.swfFileType());
    view.setCsvFileType(model.csvFileType());
    view.setZipFileType(model.zipFileType());
    view.setGzipFileType(model.gzipFileType());
    view.setHtmlFileType(model.htmlFileType());
    view.setXmlFileType(model.xmlFileType());

    view.setActivescanScanCheck(model.activescanScanCheck());
    view.setImagetragickScanCheck(model.imagetragickScanCheck());
    view.setMagickScanCheck(model.magickScanCheck());
    view.setGsScanCheck(model.gsScanCheck());
    view.setLibavformatScanCheck(model.libavformatScanCheck());
    view.setPhpScanCheck(model.phpScanCheck());
    view.setJspScanCheck(model.jspScanCheck());
    view.setAspScanCheck(model.aspScanCheck());
    view.setHtaccessScanCheck(model.htaccessScanCheck());
    view.setCgiScanCheck(model.cgiScanCheck());
    view.setSsiScanCheck(model.ssiScanCheck());
    view.setXxeScanCheck(model.xxeScanCheck());
    view.setXssScanCheck(model.xssScanCheck());
    view.setEicarScanCheck(model.eicarScanCheck());
    view.setPdfInjectionScanCheck(model.pdfInjectionScanCheck());
    view.setSsrfScanCheck(model.ssrfScanCheck());
    view.setCsvInjectionScanCheck(model.csvInjectionScanCheck());
    view.setPathTraversalScanCheck(model.pathTraversalScanCheck());
    view.setPolyglotScanCheck(model.polyglotScanCheck());
    view.setFingerpingScanCheck(model.fingerpingScanCheck());
    view.setQuirksScanCheck(model.quirksScanCheck());
    view.setUrlReplacerScanCheck(model.urlReplacerScanCheck());
    view.setRecursiveUploaderScanCheck(model.recursiveUploaderScanCheck());
    view.setFuzzerScanCheck(model.fuzzerScanCheck());
    view.setDosScanCheck(model.dosScanCheck());
  }
}
