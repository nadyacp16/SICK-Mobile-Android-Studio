package com.example.acer.askatma.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.acer.askatma.activities.MainActivity;
import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.WelcomeScreen;
import com.example.acer.askatma.activities.updatePassword;
import com.example.acer.askatma.storage.SharedPrefManager;

public class Profile extends Fragment {

    private TextView txtUsernameLogin;
    private Button btnEditPassword, btnLogout;
    private SharedPrefManager sharedPrefManager;
    private String temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        txtUsernameLogin = view.findViewById(R.id.usernameprofile);
        btnEditPassword = view.findViewById(R.id.btn_editpassword);
        btnLogout = view.findViewById(R.id.btn_logout);

        temp=sharedPrefManager.getSpIdPel();
        txtUsernameLogin.setText(temp);

        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }

        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), updatePassword.class);
                getActivity().startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                SharedPrefManager.getInstance(getContext()).logout();
                                startActivity(new Intent(getContext(), WelcomeScreen.class));
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        return view;
    }
}