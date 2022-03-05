package com.lgy.client;

import com.lgy.common.Const;
import com.lgy.common.HelloPacket;
import org.tio.client.TioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientTioConfig;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Tio;
import org.tio.core.Node;

/**
 *
 * @author tanyaowu
 *
 */
public class HelloClientStarter {
    //服务器节点
    public static Node serverNode = new Node(Const.SERVER, Const.PORT);
    //handler, 包括编码、解码、消息处理
    public static ClientAioHandler tioClientHandler = new HelloClientAioHandler();
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ClientAioListener aioListener = null;
    //断链后自动连接的，不想自动连接请设为null
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    //一组连接共用的上下文对象
    public static ClientTioConfig clientTioConfig = new ClientTioConfig(tioClientHandler, aioListener, reconnConf);
    public static TioClient tioClient = null;
    public static ClientChannelContext clientChannelContext = null;
    /**
     * 启动程序入口
     */
    public static void main(String[] args) throws Exception {
        clientTioConfig.setHeartbeatTimeout(Const.TIMEOUT);
        clientTioConfig.setClientAioListener(new HelloClientAioListener());
        tioClient = new TioClient(clientTioConfig);
        clientChannelContext = tioClient.connect(serverNode);
        //连上后，发条消息玩玩
        sendAuth();
        sendQueryOrder();
        sendHeartBeat();
    }

    private static void sendAuth() throws Exception {
        HelloPacket packet = new HelloPacket();
        String auth = "  {\n" +
                "    \"service\": \"checkKey\",\n" +
                "    \"parkid\": \"20210001\",\n" +
                "    \"parkkey\": \"C80FB9B8-73E8-4C03-B300-2037F14F42C6\"" +
                "  }";

        packet.setBody(auth.getBytes(HelloPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }

    private static void sendQueryOrder() throws Exception {
        HelloPacket packet = new HelloPacket();
        String json = "{\n" +
                "    \"service\": \"query_price\",\n" +
                "    \"parkid\": \"20210001\",\n" +
                "    \"uuid\": \"ABCD1234\",\n" +
                "    \"car_number\": \"粤A12345\",\n" +
                "    \"pay_scene\": 0\n" +
                "  }";
        packet.setBody(json.getBytes(HelloPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }

    private static void sendHeartBeat() throws Exception {
        HelloPacket packet = new HelloPacket();
        String json = "  {\n" +
                "    \"service\": \"heartbeat\",\n" +
                "    \"parkid\": \"20210001\",\n" +
                "    \"time\": \"2021-02-01 09:00:00\"\n" +
                "  }";
        packet.setBody(json.getBytes(HelloPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }
}