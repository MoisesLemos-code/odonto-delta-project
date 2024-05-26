package br.com.molens.odontoDelta.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadUtil {

    private static Map<String, String> _mimeTypes = new HashMap<String, String>();

    static {
        _mimeTypes = new HashMap<String, String>();
        _mimeTypes.put("java", "text/plain");
        _mimeTypes.put("class", "application/java");
        _mimeTypes.put("txt", "text/plain");
        _mimeTypes.put("xml", "application/xml");
        _mimeTypes.put("sql", "text/plain");
        _mimeTypes.put("rtf", "text/rtf");
        _mimeTypes.put("htm", "text/html");
        _mimeTypes.put("html", "text/html");
        _mimeTypes.put("shtml", "text/html");
        _mimeTypes.put("css", "text/css");
        _mimeTypes.put("pdf", "application/pdf");
        _mimeTypes.put("doc", "application/msword");
        _mimeTypes.put("xls", "application/x-msexcel");
        _mimeTypes.put("mpp", "application/vnd.ms-project");
        _mimeTypes.put("ppt", "application/mspowerpoint");
        _mimeTypes.put("pps", "application/mspowerpoint");
        _mimeTypes.put("tar", "application/x-tar");
        _mimeTypes.put("zip", "application/x-compressed");
    }

    private static String getMimeTypeForFilename(String filename) {
        if (filename == null) {
            return null;
        }
        String extension = getExtension(filename);
        return getMimeTypeForExtension(extension);
    }

    private static String getMimeTypeForExtension(String extension) {
        if (extension == null) {
            return null;
        }
        String result = (String) _mimeTypes.get(extension);
        if (result == null || result.trim().length() < 1) {
            result = "application/octet-stream";
        }
        return result;
    }

    private static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }

    public static void download(String filename,
                                String mimeType,
                                byte[] content,
                                HttpServletResponse response) {

        response.setContentType(getMimeTypeForExtension(mimeType));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "." + mimeType + "" + "\";");
        response.setContentLength(content.length);
        ServletOutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            ouputStream.write(content, 0, content.length);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
