package com.example.sotsugyou.Utils;

import android.util.Log;
import android.widget.Toast;

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
    public static final String SERVER_HOME_WINDOWS_IP = "192.168.0.186";
    public static final String SERVER_SCHOOL_MAC_IP = "10.32.3.88";
    public static final String SERVER_HOME_MAC_IP = "192.168.0.151";
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

            SocketAddress socketAddress = new InetSocketAddress(SERVER_HOME_MAC_IP, SERVER_PORT);
            client.connect(socketAddress, 2000);

            if(client.isConnected()) {

                isConnect = true;
                Log.v("ServerConnectHandler", "Connect: connected");

            }
        } catch (IOException e) {

            Log.i("ServerConnectHandler", "Connect: connect error");

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

            closeAll();

        } catch (IOException e) {
            e.printStackTrace();
            closeAll();
            return null;
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
            out.flush();
            client.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
            closeAll();
        }

    }

    private void closeStream() {

        try {

            if(out != null) {

                out.close();

            }

            if(in != null) {

                in.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void closeAll() {

        try {

            closeStream();

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
