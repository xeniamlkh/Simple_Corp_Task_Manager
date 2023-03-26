package com.example.simplecorptaskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FramePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_page);

        Intent intent = getIntent();
        String flag = intent.getStringExtra("FLAG");

        if (flag.equals("RunTeamListFragment")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout_for_fragments, new TeamListFragment())
                    .commit();
        }
    }
}