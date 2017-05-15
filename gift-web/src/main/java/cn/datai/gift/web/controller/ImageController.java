package cn.datai.gift.web.controller;

import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by H.CAAHN on 2017/5/10.
 */
@Controller
@RequestMapping("/static/images")
public class ImageController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Resource
    private FileService fileService;

    @Value("${file.path}")
    private String filePath;

    @RequestMapping(value = "view/{day}/{id:.+}", method = RequestMethod.GET)
    public void view(@PathVariable("day") String day, @PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String path = day + File.separator + id;
            this.fileService.downloadFile(filePath, path, response);
        }
        catch (BizException ex) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"flag\":false,\"message\":\"文件未找到\"}");
            logger.error("文件读取未找到(IOException): {}, {}", day, id);
        }
        catch (Exception ex) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"flag\":false,\"message\":\"文件读取失败，请联系管理员或稍后再试\"}");
            logger.error("文件读取失败: " + day +", " + id, ex);
        }
    }
}
