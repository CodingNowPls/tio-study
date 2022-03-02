package com.lgy.demo;

import java.nio.ByteBuffer;

/**
 * Created by fengch on 2018-03-22.
 */
public class ByteDemo {
    public static void main(String[] args) {
        //创建ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);

        //写数据
        byteBuffer.put("Test".getBytes());

        //ByteBuffer的三要素
        System.out.println(new String(byteBuffer.array()));
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        //读数据
        byteBuffer.position(1);
        byteBuffer.limit(3);
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());

    }
}
