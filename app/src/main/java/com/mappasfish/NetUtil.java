package com.mappasfish;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    public static boolean isWiFi(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context

                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info == null) {
            return false;
        } else {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean isOnLine(Context context) {
        boolean haveInternet = false;
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mConnectivityManager != null && mNetworkInfo != null
                    && mNetworkInfo.isConnectedOrConnecting()) {
                haveInternet = true;
            } /* end of if */
        } catch (Exception e) {
            haveInternet = false;
        } /* end of try-catch */

        return haveInternet;
    }
}
