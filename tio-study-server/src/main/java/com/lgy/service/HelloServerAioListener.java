package com.lgy.service;

import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;

/**
 * @author
 */
public class HelloServerAioListener implements ServerAioListener {


    @Override
    public boolean onHeartbeatTimeout(ChannelContext channelContext, Long aLong, int i) {
        return false;
    }

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {
        System.out.println("HelloServerAioListener onAfterConnected：" );
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int i) throws Exception {

    }

    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int i) throws Exception {

    }

    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws Exception {

    }

    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long l) throws Exception {

    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) throws Exception {

    }
}
