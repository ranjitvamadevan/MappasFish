package com.mappasfish;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Loadv23Fragment extends Fragment {
    private static final String TAG = Loadv23Fragment.class.getSimpleName();
    private Button buttonAction;
    private boolean internetStatus = false;
    private TextView noNetworkConnection;
    private TextView info;
    //private TextView buttonText;
    //private ImageView buttonImage;
    MyReceiver myReceiver;

    private Context context;

    public Loadv23Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loadv23, container, false);
        buttonAction = view.findViewById(R.id.buttonAction);
        noNetworkConnection = view.findViewById(R.id.noNetworkConnection);
        info = view.findViewById(R.id.info);

        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            //startActivity(new Intent(this, MainActivity.class));
            //finish();
            noNetworkConnection.setText("mappasfreshfish.com");
            info.setText("The most reliable e-commerce website.");
            buttonAction.setText("SHOP NOW");
            //buttonImage.setImageResource(R.drawable.ic_shopping_cart_white);
            internetStatus = true;
            Toast.makeText(context, "Network Available", Toast.LENGTH_LONG).show();

        } else {
            noNetworkConnection.setText("No Network Connection");
            info.setText("Please switch on data and connect");
            buttonAction.setText("CONNECT");
            //buttonImage.setImageResource(R.drawable.ic_wifi_white);
            internetStatus = false;
            Toast.makeText(context, "Network Not Available", Toast.LENGTH_LONG).show();


            //startActivity(new Intent(this, NoInternetActivity.class));

            //finish();

        }

        buttonAction.setOnClickListener((v) -> {
            //startActivity(new Intent(this, MainActivity.class));
            //finish();
            if (internetStatus) {
                startActivity(new Intent(context, MainActivity.class));
                getActivity().finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Check for your Internet Connection.");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }

        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onStart() {
        //Register BroadcastReceiver
        //to receive event from our service
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TestJobService.MY_ACTION);
        context.registerReceiver(myReceiver, intentFilter);
        Util.scheduleJob23(context);
        Log.d(TAG, "----------------OnStart---------------------");
        super.onStart();
    }

    @Override
    public void onStop() {
        context.unregisterReceiver(myReceiver);
        Util.cancelJob();
        Log.d(TAG, "--------------OnStop-------------------");
        super.onStop();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean datapassed = intent.getBooleanExtra("DATAPASSED", false);
            Log.d(TAG, "Data Passed :");
            System.out.println(datapassed);
            internetStatus = datapassed;

            if(datapassed){
                noNetworkConnection.setText("mappasfreshfish.com");
                info.setText("The most reliable e-commerce website.");
                buttonAction.setText("SHOP NOW");

            }else{
                noNetworkConnection.setText("No Network Connection");
                info.setText("Please switch on data and connect");
                buttonAction.setText("CONNECT");


            }
        }
    }

}
