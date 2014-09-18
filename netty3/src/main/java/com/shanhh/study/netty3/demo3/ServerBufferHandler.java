package com.shanhh.study.netty3.demo3;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import java.nio.charset.Charset;

/**
 *
 * 这里其实，服务端是否分段发送并不会影响输出结果，
 * 也就是说，你一次性的把"Hi, I'm client."这段信息发送过来，输出的结果也是一样的。
 * 这就是开头说的，传输的是流，不分包。而只在于你如何分段读写。
 *
 * @author dan.shan
 * @since 2014-09-18 11:44
 */
public class ServerBufferHandler extends ClientBufferHandler {

    /**
     * 用户接受客户端发来的消息，在有客户端消息到达时触发
     *
     * @author lihzh
     * @alia OneCoder
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        // 五位读取
        while (buffer.readableBytes() >= 5) {
            ChannelBuffer tempBuffer = buffer.readBytes(5);
            System.out.println(tempBuffer.toString(Charset.defaultCharset()));
        }
        // 读取剩下的信息
        System.out.println(buffer.toString(Charset.defaultCharset()));
    }

}
