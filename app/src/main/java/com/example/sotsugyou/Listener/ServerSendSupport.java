package com.example.sotsugyou.Listener;

import com.example.sotsugyou.Utils.ServerConncetHandler;

import java.net.SocketException;

public class ServerSendSupport extends Thread{

    private String sendMsg;
    private String msgFromServer = "";
    private boolean isOver;

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Override
    public void run() {

        isOver = false;
        ServerConncetHandler serverConncetHandler = null;

        try {
            serverConncetHandler = new ServerConncetHandler();
            isOver = serverConncetHandler.connect();
            serverConncetHandler.sendMsg(sendMsg);
            msgFromServer = serverConncetHandler.rcvMsg();

        } catch (SocketException e) {

        }

        serverConncetHandler.disConnect();

    }

    public String getMsgFromServer() {
        return msgFromServer;
    }

    public boolean isOver() {
        return isOver;
    }
}
