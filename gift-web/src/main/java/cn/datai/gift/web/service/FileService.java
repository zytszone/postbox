package cn.datai.gift.web.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by H.CAAHN on 2017/5/10.
 */
public interface FileService {
    /**
     * 文件下载（写入到response中）
     * @param filePath
     * @param response
     */
    void downloadFile(String dicPath, String filePath, HttpServletResponse response);
}
