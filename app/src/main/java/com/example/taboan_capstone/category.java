package com.example.taboan_capstone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class category extends Fragment {

    Button productList_btn;


    public category() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        productList_btn = v.findViewById(R.id.view_vf_btn);

        productList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegfruit vegfruitf = new vegfruit();
                FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_slide_right, R.anim.exit_slide_left, R.anim.enter_slide_left, R.anim.exit_slide_right);
                transaction.replace(R.id.mainLayout,vegfruitf);
                transaction.commit();

            }
        });

        return v;
    }
}