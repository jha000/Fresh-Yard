package com.example.milkaggregatorapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class productFragment extends Fragment {

    Intent intent,intent2;
    Button button,button2,button3,button4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);



        intent = new Intent(getActivity(), selection.class);
        final Button button = (Button) view.findViewById(R.id.buy1);

        intent2 = new Intent(getActivity(), selection2.class);
        final Button button2 = (Button) view.findViewById(R.id.small);

        
        final Button button3 = (Button) view.findViewById(R.id.int1);

       
        final Button button4 = (Button) view.findViewById(R.id.int2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Thank You for the response", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Thank You for the response", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}