package cn.datai.gift.web.service.impl;

import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.service.FileService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by H.CAAHN on 2017/5/10.
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void downloadFile(String dicPath, String filePath, HttpServletResponse response) {
        if (!dicPath.endsWith("/") && !dicPath.endsWith("\\")) {
            dicPath += File.separator;
        }
        filePath = dicPath + filePath;
        InputStream input = null;
        try {
            input = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
            int readLength = -1;
            byte[] bytes = new byte[4096];
            while ((readLength = input.read(bytes)) > 0) {
                out.write(bytes, 0, readLength);
            }
            out.flush();
        }
        catch (IOException ex) {
            throw new BizException(ex);
        }
        finally {
            try {
                if (input != null) {
                    input.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
