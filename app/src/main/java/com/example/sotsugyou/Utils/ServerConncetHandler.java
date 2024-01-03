package com.example.sotsugyou.Utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * サーバと繋がるクラス
 * メインスレッドにインスタンスをつかわないよう
 */
public class ServerConncetHandler {

    //TODO 设置IP
    public static final String SERVER_IP = "192.168.0.186";
    //TODO 设置端口
    public static final int SERVER_PORT = 1000;
    private Socket client;
    private OutputStream out;
    private InputStream in;

    private int serverStatus = 1;

    public boolean connect() {

        boolean isConnect = false;

        try {

            if(client == null) {

                client = new Socket();

            }

            SocketAddress socketAddress = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            client.connect(socketAddress, 2000);

            if(client.isConnected()) {

                isConnect = true;
                Log.v("ServerConnectHandler", "Connect: connected");

            }
        } catch (IOException e) {

            Log.i("ServerConnectHandler", "Connect: connect error");
            e.printStackTrace();

        }

        return isConnect;

    }

    /**
     * めっせーじを送る
     * @param msg　送りたいメッセージ
     */
    public void sendMsg(String msg) {

        try {

            if(out == null) {

                out = client.getOutputStream();

            }
            out.write(msg.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void closeAll() {

        try {

            if(out != null) {

                out.close();

            }

            if(in != null) {

                in.close();

            }

            if(client != null) {

                client.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void disConnect() {
        
        closeAll();

    }

    public int getServerStatus() {
        return serverStatus;
    }
}
