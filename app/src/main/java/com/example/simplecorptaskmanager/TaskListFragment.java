package com.example.simplecorptaskmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TaskListFragment extends Fragment {
    Button btnAddNewTask;
    Button btnReturnToTodayPage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddNewTask = view.findViewById(R.id.btn_add_new_task);
        btnReturnToTodayPage = view.findViewById(R.id.btn_return_today_page);

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_for_fragments, new NewTaskFragment())
                        .commit();
            }
        });

        btnReturnToTodayPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TodayPageActivity.class);
                startActivity(intent);
            }
        });
    }
}