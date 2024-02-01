package com.example.sotsugyou.Utils;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.databinding.FragmentMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

public class BluetoothHandler {

    public static final int REQUESTCODE_ENABLE_BLUETOOTH = 10000;
    public static boolean isDollConnected = false;

    private BluetoothManager manager;
    private BluetoothAdapter adapter;
    private Context context;
    private Handler handler;
    private BluetoothConnectEventListener listener;

    //TODO　ハードウェアの名前を設定
    public static final String BLUETOOTH_NAME = "M5_0125";

    public BluetoothHandler(Context context) {
        this.context = context;
        initAdapter();
    }

    /**
     * context nullpoint エラー出たら、activityにこのメソッドを利用してください。
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
        initAdapter();
    }

    private void initAdapter() {

        if (context != null) {

            manager = context.getSystemService(BluetoothManager.class);
            adapter = manager.getAdapter();

            handler = new Handler(Looper.getMainLooper());

        }

    }

    /**
     * ハードウェアは見えるように
     * @return
     */
    public boolean enableVisibly() {

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;
        }
        context.startActivity(discoverableIntent);

        return true;

    }

    public void setOnConnectedEventListener(BluetoothConnectEventListener listener) {
        this.listener = listener;
    }

    /**
     * ブルートゥース起動
     * @param activity
     * @return false 起動失敗
     */
    public boolean enableBluetooth(AppCompatActivity activity) {

        if (adapter == null) {

            Log.w("Blutooth Handler", "ブルートゥースはサポートできない");
            return false;
        }

        if (!adapter.isEnabled()) {

            Log.i("Bluetooth Handler", "bluetooth loading");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            String[] permissions = {android.Manifest.permission.BLUETOOTH_CONNECT, android.Manifest.permission.BLUETOOTH, android.Manifest.permission.BLUETOOTH_SCAN, android.Manifest.permission.BLUETOOTH_ADVERTISE};

            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Log.e("Bluetooth handler", "permission error");
                ActivityCompat.requestPermissions(activity, permissions, 1);
            }
            activity.startActivityForResult(intent, REQUESTCODE_ENABLE_BLUETOOTH);

        }

        return true;

    }

    public void searchBondedHardWare() {

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.BLUETOOTH}, 100);


        }

        boolean isSearched = false;

        Set<BluetoothDevice> devicesSet = adapter.getBondedDevices();
        for (BluetoothDevice bluetoothDevice : devicesSet) {

            if (BLUETOOTH_NAME.equals(bluetoothDevice.getName())) {

                ConnectThead connectThead = new ConnectThead(bluetoothDevice);
                connectThead.start();
                isSearched = true;

            }

        }

        if(!isSearched) {

            listener.onDonthasDoll();

        }

    }

    class ConnectThead extends Thread {

        private final BluetoothSocket socket;
        private final BluetoothDevice device;
        private ConnectedThead connectedThead;

        public ConnectThead(BluetoothDevice device) {
            this.device = device;

            BluetoothSocket tmp = null;

            try {

                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                }
                tmp = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());

            } catch (IOException e) {
                e.printStackTrace();
            }

            socket = tmp;

        }

        public void run() {

            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {

            }
            adapter.cancelDiscovery();

            try {

                socket.connect();
                onConnected();
                isDollConnected = true;

            } catch (IOException e) {

                Log.w("BluetoothHandler-connectthead", "bluetooth connect error");
                try {

                    socket.close();

                } catch (IOException ex) {

                    Log.w("BluetoothHandler-connectThead", "bluetooth close error");

                }

                onDisConnected();
                onConnectionRetry();
                return;

            }

            managerConnectSocket();

        }

        private void managerConnectSocket() {

            connectedThead = new ConnectedThead(socket);
            connectedThead.start();

        }

    }

    class ConnectedThead extends Thread{

        private final BluetoothSocket socket;
        private final InputStream inputStream;
        private final OutputStream outputStream;
        private byte[] bytes;

        public ConnectedThead(BluetoothSocket socket) {
            this.socket = socket;

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {

                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

            } catch (IOException e) {


                try {

                    inputStream.close();
                    outputStream.close();

                } catch (IOException ex) {
                    Log.w("BluetoothHandler-connected", "ConnectedThead: close error");
                }

            }

            this.inputStream = inputStream;
            this.outputStream = outputStream;

        }

        public void run() {

            byte[] buffer = new byte[1024];
            while (true) {

                try {
                    int numBytes = inputStream.read(buffer);

                    isDollConnected = true;
                    if (numBytes > 0) {
                        String str = new String(buffer, 0, numBytes);
                        Log.i("bluetooth", str);
                        SoundPlay soundPlay = MainActivity.getApp().getSoundPlay();

                        if(soundPlay != null) {

                            //TODO 信息处理
                            soundPlay.play(str);

                        }

                    }

                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {

                    e.printStackTrace();
                    closeStream();
                    isDollConnected = false;
                    onDisConnected();
                    onConnectionRetry();
                    Log.i("BluetoothHandler", " doll connected is  false");

                    break;
                }

            }
        }

        public void closeStream() {

            try {

                if(inputStream != null) {
                    inputStream.close();
                }

                if(outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }





        }

        public byte[] getBytes() {
            if(bytes != null) {

                return bytes;

            }

            return null;
        }
    }

    private void onConnected() {

        if(listener != null) {

            listener.onConnected();

        }

    }
    private void onDisConnected() {

        if(listener != null) {

            listener.onDisConnected();

        }

    }
    private void onConnectionRetry() {

        if(listener != null) {

            listener.onConnectionRetry();

        }

    }

}
