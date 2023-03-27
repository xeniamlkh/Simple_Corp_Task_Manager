package com.example.simplecorptaskmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamListFragment extends Fragment {
    private final String TAG = "Simple Corp Tag Manager LOG";
    private Button btnAddNewUser;
    private Button btnReturnToTodayPage;

    private UsersRecyclerViewAdapter usersRecyclerViewAdapter;
    private ArrayList<HashMap> listTemp;
    private ArrayList<User> dataSource;
    private RecyclerView recyclerViewUsers;
    private FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamListFragment newInstance(String param1, String param2) {
        TeamListFragment fragment = new TeamListFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddNewUser = view.findViewById(R.id.btn_add_new_user);
        btnReturnToTodayPage = view.findViewById(R.id.btn_return);

        recyclerViewUsers = view.findViewById(R.id.team_page_recycler_view);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(view.getContext()));

        db = FirebaseFirestore.getInstance();
        dataSource = new ArrayList<>();
        listTemp = new ArrayList<>();

        usersRecyclerViewAdapter = new UsersRecyclerViewAdapter(view.getContext(), dataSource);
        recyclerViewUsers.setAdapter(usersRecyclerViewAdapter);

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listTemp.add((HashMap) document.getData().get("user"));
                            }
                        } else {
                            Log.d(TAG, "Error getting document ", task.getException());
                        }
                        fillDataSourceArray();
                    }
                });

        btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_for_fragments, new NewUserFragment())
                        .commit();
            }
        });

        btnReturnToTodayPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TodayPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fillDataSourceArray() {
        for (int i = 0; i < listTemp.size(); i++) {
            String userName = listTemp.get(i).get("name").toString();
            String userJobTitle = listTemp.get(i).get("jobTitle").toString();
            User user = new User(userName, userJobTitle);
            dataSource.add(user);
            usersRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}