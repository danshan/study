package com.shanhh.study.hessian.demo.compression;

import com.caucho.hessian.io.Deflation;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.shanhh.study.hessian.demo.serialization.Car;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author dan.shan
 * @since 2014-09-18 20:00
 */
public class Compression {

    private byte[] deflation(Object obj) throws IOException {
        Deflation envelope = new Deflation();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);

        out = envelope.wrap(out);

        out.startMessage();

        out.writeObject(obj);

        out.completeMessage();
        out.close();

        byte []data = bos.toByteArray();
        return data;
    }

    private <T> T inflation(byte[] data, Class<T> clazz) throws IOException {
        Deflation envelope = new Deflation();
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        Hessian2Input in = new Hessian2Input(bin);

        in = envelope.unwrap(in);

        in.startMessage();

        T obj = (T)in.readObject();

        in.completeMessage();

        in.close();
        bin.close();

        return obj;
    }

    public static void main(String[] args) throws IOException {
        Compression demo = new Compression();

        Car before = new Car(Car.Model.EDSEL, Car.Color.GREEN, 1954);
        byte[] data = demo.deflation(before);

        Car after = demo.inflation(data, Car.class);
        System.out.println(after);
    }
}
