package model.utilities;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Constants {
  public static final String DOWNLOAD_ME = "DOWNLOAD_ME";
  public static final String MARKER_URL_CONTENT = "A_FILENAME_PLACEHOLDER_FOR_THE_DESCRIPTION_NeVeR_OcCuRs_iN_ReAl_WoRlD_DaTa";
  public static final String ORIGINAL_FILE_EXTENSION = "ORIGINAL_FILE_EXTENSION";
  public static final String MARKER_COLLAB_URL = "http://example.org/";
  public static final String MARKER_CACHE_DEFEAT_URL = "https://example.org/cachedefeat/";
  public static final String NEWLINE = "\r\n";
  public static final Pattern REGEX_PASSWD = Pattern.compile("[^:]{3,20}:[^:]{1,100}:\\d{0,20}:\\d{0,20}:[^:]{0,100}:[^:]{0,100}:[^:]*$");

  // TODO: If we just add \\ the extension uploads *a lot more* files... worth doing?
  public static final String[] PROTOCOLS_HTTP = {
    // 'ftp://',
    // 'smtp://',
    // 'mailto://',
    // The following is \\ for Windows servers...
    // '\\\\',
    "http://",
    "https://"
  };

  public static final int MAX_SERIALIZED_DOWNLOAD_MATCHERS = 500;
  public static final int MAX_RESPONSE_SIZE = 300000;  // 300kb

  // ReDownloader constants:
  public static final String[] REDOWNLOADER_URL_BAD_HEADERS = {"content-length:", "accept:", "content-type:", "referer:"};
  public static final String REDOWNLOADER_FILENAME_MARKER = "${FILENAME}";
  public static final String SEARCH_STR_MARKER_START = "${PYTHONSTR:";
  public static final String SEARCH_STR_MARKER_END = "}";
  public static final List<String> KNOWN_FUZZ_STRINGS = Arrays.asList(
    "A".repeat(256),
    "A".repeat(1024),
    "A".repeat(4096),
    "A".repeat(20000),
    "A".repeat(65535),
    "%x".repeat(256),
    "%n".repeat(256),
    "%s".repeat(256),
    "%s%n%x%d".repeat(256),
    "%s".repeat(256),
    "%.1024d",
    "%.2048d",
    "%.4096d",
    "%.8200d",
    "%99999999999s",
    "%99999999999d",
    "%99999999999x",
    "%99999999999n",
    "%99999999999s".repeat(200),
    "%99999999999d".repeat(200),
    "%99999999999x".repeat(200),
    "%99999999999n".repeat(200),
    "%08x".repeat(100),
    "%%20s".repeat(200),
    "%%20x".repeat(200),
    "%%20n".repeat(200),
    "%%20d".repeat(200),
    "%#0123456x%08x%x%s%p%n%d%o%u%c%h%l%q%j%z%Z%t%i%e%g%f%a%C%S%08x%%#0123456x%%x%%s%%p%%n%%d%%o%%u%%c%%h%%l%%q%%j%%z%%Z%%t%%i%%e%%g%%f%%a%%C%%S%08x",
    "'",
    "\\",
    "<",
    "+",
    "%",
    "$",
    "`"
  );

  // The following var is a special case when we detect that the request doesn't include
  // the filename or content-type (e.g. Vimeo image avatar upload), so we don't do 30
  // identical requests with the exact same content. See the get_types function.
  public static final String[] NO_TYPES = {"", "", ""};

  public static final String[][] IM_MVG_TYPES = {
    {"", "", ""},
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", "", "image/png"},
    {"", "", "image/pjpeg"},
    {"", "", "image/x-citrix-pjpeg"},
    {"", "", "image/x-citrix-gif"},
    {"", ".mvg", ""},
    {"", ".mvg", "image/svg+xml"},
    {"", ".png", "image/png"},
    // {"", ".jpeg", "image/jpeg"},
    {"mvg:", ".mvg", ""},
    // {"mvg:", ".mvg", "image/svg+xml"},
  };

  // Xbm black/white pictures
  public static final String[][] XBM_TYPES = {
    {"", "", ""},
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ".xbm", ""},
    {"", ".xbm", "image/x-xbm"},
    {"", ".xbm", "image/png"},
    {"xbm:", ORIGINAL_FILE_EXTENSION, ""},
  };

  public static final String[][] GHOSTSCRIPT_TYPES = {
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ".gs", ""},
    {"", ".eps", ""},
    {"", ORIGINAL_FILE_EXTENSION, "text/plain"},
    {"", ".jpeg", "image/jpeg"},
    {"", ".png", "image/png"},
  };

  public static final String[][] LIB_AV_FORMAT_TYPES = {
    // {"", "", ""},
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ORIGINAL_FILE_EXTENSION, "audio/mpegurl"},
    {"", ORIGINAL_FILE_EXTENSION, "video/x-msvideo"},
    // {"", ".m3u8", "application/vnd.apple.mpegurl"},
    {"", ".m3u8", "application/mpegurl"},
    // {"", ".m3u8", "application/x-mpegurl"},
    {"", ".m3u8", "audio/mpegurl"},
    // {"", ".m3u8", "audio/x-mpegurl"},
    {"", ".avi", "video/x-msvideo"},
    {"", ".avi", ""},
  };

  public static final String[][] EICAR_TYPES = {
    // {"", "", ""},
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ".exe", ""},
    {"", ".exe", "application/x-msdownload"},
    // {"", ".exe", "application/octet-stream"},
    // {"", ".exe", "application/exe"},
    // {"", ".exe", "application/x-exe"},
    // {"", ".exe", "application/dos-exe"},
    // {"", ".exe", "application/msdos-windows"},
    // {"", ".exe", "application/x-msdos-program"},
    {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ORIGINAL_FILE_EXTENSION, "application/x-msdownload"},
    // {"", self._magick_original_extension, "application/octet-stream"},
    // {"", self._magick_original_extension, "application/exe"},
    // {"", self._magick_original_extension, "application/x-exe"},
    // {"", self._magick_original_extension, "application/dos-exe"},
    // {"", self._magick_original_extension, "application/msdos-windows"},
    // {"", self._magick_original_extension, "application/x-msdos-program"},
  };

  public static final String[][] PL_TYPES = {
    // {"", ORIGINAL_FILE_EXTENSION, ""},
    {"", ORIGINAL_FILE_EXTENSION, "text/x-perl-script"},
    {"", ".pl", ""},
    {"", ".pl", "text/x-perl-script"},
    {"", ".cgi", ""}
    // {"", ".cgi", "text/x-perl-script"},
  };

  public static final String[][] PY_TYPES = {
      // {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ORIGINAL_FILE_EXTENSION, "text/x-python-script"},
      {"", ".py", ""},
      {"", ".py", "text/x-python-script"},
      {"", ".cgi", ""}
  };

  public static final String[][] RB_TYPES = {
      // {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ORIGINAL_FILE_EXTENSION, "text/x-ruby-script"},
      {"", ".rb", ""},
      {"", ".rb", "text/x-ruby-script"}
  };

  public static final String[][] HTACCESS_TYPES = {
      {"", "", ""},
      {"", "%00" + ORIGINAL_FILE_EXTENSION, ""},
      {"", "\u0000" + ORIGINAL_FILE_EXTENSION, ""},
      {"", "", "text/plain"},
      {"", "%00" + ORIGINAL_FILE_EXTENSION, "text/plain"},
      {"", "\u0000" + ORIGINAL_FILE_EXTENSION, "text/plain"},
  };

  public static final String[][] PDF_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ORIGINAL_FILE_EXTENSION, "application/pdf"},
      {"", ".pdf", ""},
      {"", ".pdf", "application/pdf"},
  };

  public static final String[][] URL_TYPES = {
      // {"", ORIGINAL_FILE_EXTENSION, ""},
      // {"", ORIGINAL_FILE_EXTENSION, "application/octet-stream"},
      {"", ".URL", ""}
      // {"", ".URL", "application/octet-stream"},
  };

  public static final String[][] INI_TYPES = {
      // {"", ORIGINAL_FILE_EXTENSION, ""},
      // {"", ORIGINAL_FILE_EXTENSION, "application/octet-stream"},
      {"", ".ini", ""}
      // {"", ".URL", "application/octet-stream"},
  };

  public static final String[][] ZIP_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ORIGINAL_FILE_EXTENSION, "application/zip"},
      {"", ".zip", ""},
      {"", ".zip", "application/zip"},
  };

  public static final String[][] CSV_TYPES = {
      // {"", "", ""},
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".csv", ""},
      {"", ".csv", "text/csv"},
      // {"", self._marker_orig_ext, ""},
      // {"", self._marker_orig_ext, "text/csv"},
  };

  public static final String[][] EXCEL_TYPES = {
      // {"", "", ""},
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".xls", ""},
      {"", ".xls", "application/vnd.ms-excel"}
      // {"", ORIGINAL_FILE_EXTENSION, ""},
      // {"", ORIGINAL_FILE_EXTENSION, "text/application/vnd.ms-excel"},
  };

  public static final String[][] IQY_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".iqy", ""},
      {"", ".iqy", "application/vnd.ms-excel"},
  };

  // Server Side Include types
// See also what file extensions the .htaccess module would enable!
// It is unlikely that a server accepts content type text/html...
  public static final String[][] SSI_TYPES = {
      // {"", ".shtml", "text/plain"},
      {"", ".shtml", "text/html"},
      // {"", ".stm", "text/html"},
      // {"", ".shtm", "text/html"},
      // {"", ".html", "text/html"},
      // {"", ORIGINAL_FILE_EXTENSION, "text/html"},
      {"", ".shtml", ""},
      {"", ".stm", ""},
      {"", ".shtm", ""},
      {"", ".html", ""},
      {"", ORIGINAL_FILE_EXTENSION, ""},
  };

  public static final String[][] ESI_TYPES = {
      {"", ".txt", "text/plain"},
      // {"", ".txt", ""},
      {"", ORIGINAL_FILE_EXTENSION, ""},
  };

  public static final String[][] SVG_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""}, // Server doesn't check file contents
      {"", ".svg", "image/svg+xml"}, // Server enforces matching of file ext and content type
      {"", ".svg", ""}, // Server doesn't check file ext
      {"", ORIGINAL_FILE_EXTENSION, "image/svg+xml"}, // Server doesn't check content-type
  };

  public static final String[][] XML_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".xml", "application/xml"},
      {"", ".xml", "text/xml"},
      // {"", ".xml", "text/plain"},
      {"", ".xml", ""},
      {"", ORIGINAL_FILE_EXTENSION, "text/xml"},
  };

  public static final String[][] SWF_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".swf", "application/x-shockwave-flash"},
      {"", ".swf", ""},
      {"", ORIGINAL_FILE_EXTENSION, "application/x-shockwave-flash"},
  };

  public static final String[][] HTML_TYPES = {
      {"", ORIGINAL_FILE_EXTENSION, ""},
      {"", ".htm", ""},
      {"", ".html", ""},
      {"", ".htm", "text/html"},
      // {"", ".html", "text/html"},
      {"", ".html", "text/plain"},
      {"", ".xhtml", ""},
      // {"", ORIGINAL_FILE_EXTENSION, "text/html"},
  };

}

