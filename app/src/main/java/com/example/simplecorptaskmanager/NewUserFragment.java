package com.example.simplecorptaskmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewUserFragment extends Fragment {

    private final String TAG = "Simple Corp Tag Manager LOG";
    private EditText userName;
    private EditText jobTitle;
    private Button btnSaveUser;
    private Button btnCancel;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userName = view.findViewById(R.id.user_name_edit_text);
        jobTitle = view.findViewById(R.id.job_title_edit_text);
        btnSaveUser = view.findViewById(R.id.btn_save_user);
        btnCancel = view.findViewById(R.id.btn_cancel_user);

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String jobT = jobTitle.getText().toString();

                User user = new User(name, jobT);
                Map<String, Object> dbUser = new HashMap<>();
                dbUser.put("user", user);

                db.collection("users")
                        .add(dbUser)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(requireActivity(), "User was successfully added",
                                        Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onSuccess: User was successfully added to DB");

                                requireActivity()
                                        .getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frame_layout_for_fragments,
                                                new TeamListFragment())
                                        .commit();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireActivity(), "User was not added!",
                                        Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onFailure: User was not added to db");
                            }
                        });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_for_fragments, new TeamListFragment())
                        .commit();
            }
        });
    }
}