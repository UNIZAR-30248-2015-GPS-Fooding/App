package com.GPS.app.fooding.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.GPS.app.fooding.MainActivity;
import com.GPS.app.fooding.R;
import com.GPS.app.fooding.connection.ClientInterface;
import com.GPS.app.fooding.data.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by coke on 15/10/2015.
 */

public class ErrorFragment extends android.support.v4.app.Fragment {

    View view;

    public ErrorFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.error_fragment, container, false);

        return view;
    }



}
