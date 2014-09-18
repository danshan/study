/**
 *
 * Netty中的消息传递，都必须以字节的形式，以ChannelBuffer为载体传递。
 * 简单的说，就是你想直接写个字符串过去，对不起，抛异常。
 * 虽然，Netty定义的writer的接口参数是Object的，这可能也是会给新上手的朋友容易造成误会的地方。
 *
 * @author dan.shan
 * @since 2014-09-18 11:40
 */
package com.shanhh.study.netty3.demo2;