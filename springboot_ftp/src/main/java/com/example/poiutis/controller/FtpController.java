package com.example.poiutis.controller;

import com.example.poiutis.service.IFileService;
import com.example.poiutis.utils.PropertiesUtil;
import com.example.poiutis.utils.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Maps;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 上传 ftp
 * @author wushaopei
 *
 */
@RestController
@RequestMapping(value = "/webcode")
public class FtpController {

    private static Logger logger = LoggerFactory.getLogger(FtpController.class);

    @Autowired
    private IFileService iFileService;

    @RequestMapping("/upload/do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file,"C:\\ftpfile\\upload");
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
    }
}