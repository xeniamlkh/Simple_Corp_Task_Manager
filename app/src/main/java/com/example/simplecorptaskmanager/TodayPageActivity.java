package com.example.simplecorptaskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TodayPageActivity extends AppCompatActivity {
    Button buttonOpenTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_page);

        buttonOpenTasks = findViewById(R.id.btn_open_tasks);
        buttonOpenTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodayPageActivity.this,
                        FramePageActivity.class);
                intent.putExtra("FLAG", "RunTaskListFragment");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.our_team_btn:
                Intent intent = new Intent(TodayPageActivity.this,
                        FramePageActivity.class);
                intent.putExtra("FLAG", "RunTeamListFragment");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}