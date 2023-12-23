package controller.tabControllers;

import model.BaseConfigModel;
import view.tabs.BaseConfigTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BaseConfigController{

    public BaseConfigController(BaseConfigModel baseConfigModel, BaseConfigTab baseConfigTab) {
        model = baseConfigModel;
        view  = baseConfigTab;
        addSaveButtonListener();
        addResetButtonListener();
        updateView();
    }
    
    public String getTabName() {
        return BaseConfigTab.TAB_NAME;
    }

    public JPanel getTab() {
        return view;
    }

    private final BaseConfigModel model;
    private final BaseConfigTab   view;
    
    private void updateView(){
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


    private void addSaveButtonListener() {
        view.addSaveButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // File Settings
                model.setReplaceContentType             (view.getReplaceContentType().isSelected());
                model.setReplaceFileName                (view.getReplaceFileName().isSelected());
                model.setReplaceFileSize                (view.getReplaceFileSize().isSelected());
                
                model.setWgetCurlPayloads               (view.getWgetCurlPayloads().isSelected());
                model.setSleepTime(Short.parseShort     (view.getSleepTime()));
                
                model.setThrottleTime(Short.parseShort  (view.getThrottleValue()));
                model.setAddToLoggingTab                (view.getAddToLoggingChkBox().isSelected());
                
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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                updateView();
            }
        });
    }
}
