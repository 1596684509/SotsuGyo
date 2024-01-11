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
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothHandler {

    public static final int REQUESTCODE_ENABLE_BLUETOOTH = 10000;

    private BluetoothManager manager;
    private BluetoothAdapter adapter;
    private Context context;
    private Handler handler;

    //TODO　ハードウェアの名前を設定
    public static final String BLUETOOTH_NAME = "test";

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

            String[] permissions = {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_ADVERTISE};

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Log.e("Bluetooth handler", "permission error");
                ActivityCompat.requestPermissions(activity, permissions, 1);
                return false;
            }
            activity.startActivityForResult(intent, REQUESTCODE_ENABLE_BLUETOOTH);

        }

        return true;

    }

    public void searchBondedHardWare() {



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

        }
        Set<BluetoothDevice> devicesSet = adapter.getBondedDevices();
        for (BluetoothDevice bluetoothDevice : devicesSet) {

            if (BLUETOOTH_NAME.equals(bluetoothDevice.getName())) {

                ConnectThead connectThead = new ConnectThead(bluetoothDevice);
                connectThead.start();

            }

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

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                }
                tmp = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());

            } catch (IOException e) {
                e.printStackTrace();
            }

            socket = tmp;

        }

        public void run() {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {

            }
            adapter.cancelDiscovery();

            try {

                socket.connect();

            } catch (IOException e) {

                Log.w("BluetoothHandler-connectthead", "bluetooth connect error");
                try {

                    socket.close();

                } catch (IOException ex) {

                    Log.w("BluetoothHandler-connectThead", "bluetooth close error");

                }

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

            bytes = new byte[1024];

            int numBytes;

            while(true) {

                try {

                    numBytes = inputStream.read(bytes);
                    Message readMsg = handler.obtainMessage(
                            0, numBytes, -1,
                            bytes);
                    readMsg.sendToTarget();

                    //TODO 获取的数据处理

                } catch (IOException e) {
                    Log.w("BluetoothHandler-connected", "run: inputStream error");
                }

            }

        }

        public byte[] getBytes() {
            if(bytes != null) {

                return bytes;

            }

            return null;
        }
    }



}
