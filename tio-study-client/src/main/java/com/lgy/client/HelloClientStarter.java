package com.lgy.client;

import com.lgy.common.Const;
import com.lgy.common.HelloPacket;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;

import java.util.Scanner;


/**
 * Created by fengch on 2018-03-16.
 */
public class HelloClientStarter {
    //服务器节点
    public static Node serverNode = new Node(Const.SERVER, Const.PORT);

    //handler, 包括编码、解码、消息处理
    public static ClientAioHandler aioClientHandler = new HelloClientAioHandler();

    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ClientAioListener aioListener = null;

    //断链后自动连接的，不想自动连接请设为null
    private static ReconnConf reconnConf = new ReconnConf(5000L);

    //一组连接共用的上下文对象
    public static ClientGroupContext clientGroupContext = new ClientGroupContext(aioClientHandler, aioListener, reconnConf);

    public static AioClient aioClient = null;
    public static ClientChannelContext clientChannelContext = null;

    /**
     * 启动程序入口
     */
    public static void main(String[] args) throws Exception {
        clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
        aioClient = new AioClient(clientGroupContext);
        clientChannelContext = aioClient.connect(serverNode);
        //连上后，发条消息玩玩

        Scanner input = new Scanner(System.in);
        String val = null;
        do{
            System.out.print("请输入：");
            val = input.next();

            send(val);

        }while(!val.equals("quert"));
        input.close(); // 关闭资源
    }

    private static void send(String msg) throws Exception {
        HelloPacket packet = new HelloPacket();
        packet.setBody(msg.getBytes(HelloPacket.CHARSET));
        Aio.send(clientChannelContext, packet);
    }
}