package com.shanhh.study.hessian.demo.upload;

import com.caucho.hessian.client.HessianProxyFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @author dan.shan
 * @since 2014-09-18 14:15
 */
public class UploadClient {


    private static final String url = "http://localhost:8080/upload";

    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        HessianProxyFactory factory = new HessianProxyFactory();
        UploadService uploader = (UploadService) factory.create(UploadService.class, url);
        InputStream data = new BufferedInputStream(new FileInputStream("/etc/profile"));
        uploader.upload("profile", data);
    }

}
