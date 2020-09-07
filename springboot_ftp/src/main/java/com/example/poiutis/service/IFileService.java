package com.example.poiutis.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName IFileService
 * @Description TODO
 * @Author wushaopei
 * @Date 2020/9/6 15:24
 * @Version 1.0
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}

