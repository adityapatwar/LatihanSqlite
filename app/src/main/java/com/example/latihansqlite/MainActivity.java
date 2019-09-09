package com.example.latihansqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        Fragment fg = new main ();
        FragmentManager fgm = getSupportFragmentManager ();
        FragmentTransaction fgt = fgm.beginTransaction ();

        fgt.add (R.id.framecontainer,fg);
        fgt.commit ();

    }
}
