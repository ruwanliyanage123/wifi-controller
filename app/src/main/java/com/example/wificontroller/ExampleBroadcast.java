package com.example.wificontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class ExampleBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager;
        String connectedSsid;
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())){
            NetworkInfo netInfo = intent.getParcelableExtra (WifiManager.EXTRA_NETWORK_INFO);
            if (ConnectivityManager.TYPE_WIFI == netInfo.getType ()) {
                wifiManager = (WifiManager) context.getSystemService (Context.WIFI_SERVICE);
                WifiInfo info = wifiManager.getConnectionInfo ();
                connectedSsid = info.getSSID();
                String authorizedSsidToCompare = "\"ruwan\"";
                if(!connectedSsid.equals(authorizedSsidToCompare)){
                    wifiManager.disconnect();
                    if(connectedSsid!=null) {
                        Toast.makeText(context, "Disconnected! " + connectedSsid + "is not an authorized wifi network", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context,"Connected to authorized wifi network",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
