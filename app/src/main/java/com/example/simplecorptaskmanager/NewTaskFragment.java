package com.example.simplecorptaskmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewTaskFragment extends Fragment {

    private Button btnPickUser;
    private Button btnPickDate;
    private Button btnCancel;
    private Button btnSave;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewTaskFragment newInstance(String param1, String param2) {
        NewTaskFragment fragment = new NewTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPickDate = view.findViewById(R.id.pick_date);
        btnPickUser = view.findViewById(R.id.pick_user);
        btnCancel = view.findViewById(R.id.btn_cancel_edit_task);
        btnSave = view.findViewById(R.id.btn_save_new_task);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_for_fragments, new TaskListFragment())
                        .commit();
            }
        });

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open calendar
            }
        });

        btnPickUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open users list and pick up a user
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO save to the DB
            }
        });
    }
}