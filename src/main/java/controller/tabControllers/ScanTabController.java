package controller.tabControllers;

import model.ScanModel;
import view.tabs.ScanTab;

public class ScanTabController {
    public ScanTabController(ScanModel scanModel, ScanTab scanTabView) {
        _model = scanModel;
        _view  = scanTabView;
        updateView();
    }
    
    private void updateView(){
        _view.baseConfigTemplate().setReplaceFileName(_model.baseConfigModel().replaceFileName());
        _view.baseConfigTemplate().setReplaceFileSize(_model.baseConfigModel().replaceFileSize());
        _view.baseConfigTemplate().setReplaceContentType(_model.baseConfigModel().replaceContentType());
        _view.baseConfigTemplate().setAddToLoggingChkBox(_model.baseConfigModel().addToLoggingTab());
        _view.baseConfigTemplate().setWgetCurlPayloads(_model.baseConfigModel().wgetCurlPayloads());
        _view.baseConfigTemplate().setSleepTime(_model.baseConfigModel().sleepTime());
        _view.baseConfigTemplate().setThrottleValue(_model.baseConfigModel().throttleTime());
        
        _view.baseConfigTemplate().setGifFileType(_model.baseConfigModel().gifFileType());
        _view.baseConfigTemplate().setPngFileType(_model.baseConfigModel().pngFileType());
        _view.baseConfigTemplate().setJpegFileType(_model.baseConfigModel().jpegFileType());
        _view.baseConfigTemplate().setTiffFileType(_model.baseConfigModel().tiffFileType());
        _view.baseConfigTemplate().setIcoFileType(_model.baseConfigModel().icoFileType());
        _view.baseConfigTemplate().setSvgFileType(_model.baseConfigModel().svgFileType());
        _view.baseConfigTemplate().setMvgFileType(_model.baseConfigModel().mvgFileType());
        _view.baseConfigTemplate().setPdfFileType(_model.baseConfigModel().pdfFileType());
        _view.baseConfigTemplate().setMp4FileType(_model.baseConfigModel().mp4FileType());
        _view.baseConfigTemplate().setDocxFileType(_model.baseConfigModel().docxFileType());
        _view.baseConfigTemplate().setXlsxFileType(_model.baseConfigModel().xlsxFileType());
        _view.baseConfigTemplate().setSwfFileType(_model.baseConfigModel().swfFileType());
        _view.baseConfigTemplate().setCsvFileType(_model.baseConfigModel().csvFileType());
        _view.baseConfigTemplate().setZipFileType(_model.baseConfigModel().zipFileType());
        _view.baseConfigTemplate().setGzipFileType(_model.baseConfigModel().gzipFileType());
        _view.baseConfigTemplate().setHtmlFileType(_model.baseConfigModel().htmlFileType());
        _view.baseConfigTemplate().setXmlFileType(_model.baseConfigModel().xmlFileType());
        
        _view.baseConfigTemplate().setActivescanScanCheck(_model.baseConfigModel().activescanScanCheck());
        _view.baseConfigTemplate().setImagetragickScanCheck(_model.baseConfigModel().imagetragickScanCheck());
        _view.baseConfigTemplate().setMagickScanCheck(_model.baseConfigModel().magickScanCheck());
        _view.baseConfigTemplate().setGsScanCheck(_model.baseConfigModel().gsScanCheck());
        _view.baseConfigTemplate().setLibavformatScanCheck(_model.baseConfigModel().libavformatScanCheck());
        _view.baseConfigTemplate().setPhpScanCheck(_model.baseConfigModel().phpScanCheck());
        _view.baseConfigTemplate().setJspScanCheck(_model.baseConfigModel().jspScanCheck());
        _view.baseConfigTemplate().setAspScanCheck(_model.baseConfigModel().aspScanCheck());
        _view.baseConfigTemplate().setHtaccessScanCheck(_model.baseConfigModel().htaccessScanCheck());
        _view.baseConfigTemplate().setCgiScanCheck(_model.baseConfigModel().cgiScanCheck());
        _view.baseConfigTemplate().setSsiScanCheck(_model.baseConfigModel().ssiScanCheck());
        _view.baseConfigTemplate().setXxeScanCheck(_model.baseConfigModel().xxeScanCheck());
        _view.baseConfigTemplate().setXssScanCheck(_model.baseConfigModel().xssScanCheck());
        _view.baseConfigTemplate().setEicarScanCheck(_model.baseConfigModel().eicarScanCheck());
        _view.baseConfigTemplate().setPdfInjectionScanCheck(_model.baseConfigModel().pdfInjectionScanCheck());
        _view.baseConfigTemplate().setSsrfScanCheck(_model.baseConfigModel().ssrfScanCheck());
        _view.baseConfigTemplate().setCsvInjectionScanCheck(_model.baseConfigModel().csvInjectionScanCheck());
        _view.baseConfigTemplate().setPathTraversalScanCheck(_model.baseConfigModel().pathTraversalScanCheck());
        _view.baseConfigTemplate().setPolyglotScanCheck(_model.baseConfigModel().polyglotScanCheck());
        _view.baseConfigTemplate().setFingerpingScanCheck(_model.baseConfigModel().fingerpingScanCheck());
        _view.baseConfigTemplate().setQuirksScanCheck(_model.baseConfigModel().quirksScanCheck());
        _view.baseConfigTemplate().setUrlReplacerScanCheck(_model.baseConfigModel().urlReplacerScanCheck());
        _view.baseConfigTemplate().setRecursiveUploaderScanCheck(_model.baseConfigModel().recursiveUploaderScanCheck());
        _view.baseConfigTemplate().setFuzzerScanCheck(_model.baseConfigModel().fuzzerScanCheck());
        _view.baseConfigTemplate().setDosScanCheck(_model.baseConfigModel().dosScanCheck());
    }
    
    
    private final ScanTab   _view;
    private final ScanModel _model;
}
