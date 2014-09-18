package com.shanhh.study.hessian.demo.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author dan.shan
 * @since 2014-09-18 14:05
 */
public class UploadServiceImpl implements UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public void upload(String filename, InputStream data) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String file = "/tmp/" + filename;

            bis = new BufferedInputStream(data);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer = new byte[8192];
            int r = bis.read(buffer, 0, buffer.length);
            while (r > 0) {
                bos.write(buffer, 0, r);
                r = bis.read(buffer, 0, buffer.length);
            }
            logger.info("writen file to {}", file);
        } catch (IOException e) {
            logger.error("File upload exception: ", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("Closing output exception: ", e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("Closing input exception: ", e);
                }
            }
        }
    }
}
