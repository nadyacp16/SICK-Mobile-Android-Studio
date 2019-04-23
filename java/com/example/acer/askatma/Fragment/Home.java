package com.example.acer.askatma.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.JsonIOException;

import com.example.acer.askatma.activities.AddKeluhan;
import com.example.acer.askatma.R;
import com.example.acer.askatma.activities.MainActivity;
import com.example.acer.askatma.models.LoginResponse;
import com.example.acer.askatma.models.User;
import com.example.acer.askatma.storage.SharedPrefManager;


public class Home extends Fragment {

    private TextView textWelcome, textUser, textWhatHappen, textHelp;
    private String temp;
    private Button btnAsk;
    private SharedPrefManager sharedPrefManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        textWelcome = view.findViewById(R.id.txtWelcome);
        textUser = view.findViewById(R.id.usernamehome);
        textWhatHappen = view.findViewById(R.id.txtWhathappened);
        textHelp = view.findViewById(R.id.txtHelp);
        btnAsk = view.findViewById(R.id.btnAsk);

        temp=sharedPrefManager.getSpIdPel();
        textUser.setText(temp);

        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddKeluhan.class);
                getActivity().startActivity(i);
            }
        });
        return view;
    }


}
