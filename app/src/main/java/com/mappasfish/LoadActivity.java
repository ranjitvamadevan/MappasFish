package com.mappasfish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoadActivity extends AppCompatActivity {
    private static final String TAG = LoadActivity.class.getSimpleName();
    private FragmentManager fragmentManager;
    //private FloatingActionButton floatingActionButton;
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        //floatingActionButton = findViewById(R.id.floating_action_button);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.rgb(255, 82, 82));

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }

        fragmentManager = getSupportFragmentManager();

        int i = android.os.Build.VERSION.SDK_INT;
        Log.d(TAG, "android version : " + i);
        if(i >= 24) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Loadv24Fragment loadv24Fragment = new Loadv24Fragment();
            fragmentTransaction.add(R.id.frameContainer,loadv24Fragment,null);
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Loadv23Fragment loadv23Fragment = new Loadv23Fragment();
            fragmentTransaction.add(R.id.frameContainer,loadv23Fragment,null);
            fragmentTransaction.commit();
        }

        /*floatingActionButton.setOnClickListener((v) -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9744041444"));
            startActivity(intent);
        });*/

    }
}
