package com.shanhh.study.hessian.demo.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author dan.shan
 * @since 2014-09-18 19:39
 */
public class Serialization {

    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);

        out.startMessage();

        out.writeObject(obj);

        out.completeMessage();
        out.close();

        byte[] data = bos.toByteArray();
        return data;
    }

    private <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        ByteArrayInputStream bin = new ByteArrayInputStream(data);
        Hessian2Input in = new Hessian2Input(bin);

        in.startMessage();

        T obj = (T) in.readObject();

        in.completeMessage();

        in.close();
        bin.close();
        return obj;
    }

    public static void main(String[] args) throws IOException {
        Serialization demo = new Serialization();

        Car before = new Car(Car.Model.EDSEL, Car.Color.GREEN, 1954);
        byte[] data = demo.serialize(before);

        Car after = demo.deserialize(data, Car.class);
        System.out.println(after);
    }
}


