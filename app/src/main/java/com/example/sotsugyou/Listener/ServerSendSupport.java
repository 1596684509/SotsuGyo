package com.example.sotsugyou.Listener;

import com.example.sotsugyou.Utils.ServerConncetHandler;

import java.net.SocketException;

public class ServerSendSupport extends Thread{

    private String sendMsg;
    private String msgFromServer = "";

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Override
    public void run() {

        ServerConncetHandler serverConncetHandler = new ServerConncetHandler();
        serverConncetHandler.connect();
        try {
            serverConncetHandler.sendMsg(sendMsg);
            msgFromServer = serverConncetHandler.rcvMsg();
        } catch (SocketException e) {
        }

        serverConncetHandler.disConnect();

    }

    public String getMsgFromServer() {
        return msgFromServer;
    }

}
