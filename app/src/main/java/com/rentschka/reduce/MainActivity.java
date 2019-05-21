package com.rentschka.reduce;

import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import java.io.StringBufferInputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Animation rotate;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        player = MediaPlayer.create(this, R.raw.fallbackring);
    }

    @Override
    public void onClick(View v) {
        EditText etZaehler = findViewById(R.id.zaehler);
        EditText etNenner = findViewById(R.id.nenner);

        String sz = etZaehler.getText().toString();
        String sn = etNenner.getText().toString();
        if(sz.length() == 0 || sn.length() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bitte Zähler und Nenner eintragen.");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        int z = Integer.parseInt(sz);
        int n = Integer.parseInt(sn);

        if(z*n != 0){
            int rest;
            int ggt = Math.abs(z);
            int divisor = Math.abs(n);
            do{
                rest = ggt % divisor;
                ggt = divisor;
                divisor = rest;
            }while(rest > 0);
            z /= ggt;
            n /= ggt;
            player.start();
        }

        etZaehler.setText(Integer.toString(z));
        etNenner.setText(Integer.toString(n));
        v.startAnimation(rotate);
    }
}
