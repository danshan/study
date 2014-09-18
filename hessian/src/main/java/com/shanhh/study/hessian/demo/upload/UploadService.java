package com.shanhh.study.hessian.demo.upload;

import java.io.InputStream;

/**
 * @author dan.shan
 * @since 2014-09-18 14:04
 */
public interface UploadService {

    void upload(String filename, InputStream data);

}
