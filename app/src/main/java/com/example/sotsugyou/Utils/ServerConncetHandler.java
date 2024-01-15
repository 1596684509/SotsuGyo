package com.example.sotsugyou.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * サーバと繋がるクラス
 * メインスレッドにインスタンスをつかわないよう
 */
public class ServerConncetHandler {

    //TODO 设置IP
    public static final String SERVER_WINDOWS_IP = "192.168.0.186";
    public static final String SERVER_MAC_IP = "10.32.2.15";
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

            SocketAddress socketAddress = new InetSocketAddress(SERVER_WINDOWS_IP, SERVER_PORT);
            client.connect(socketAddress, 20000);

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

    public String rcvMsg() {

        StringBuffer stringBuffer;

        try {

            if(in == null) {

                in = client.getInputStream();

            }

            stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String line;
            while((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line);

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuffer.toString();

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
