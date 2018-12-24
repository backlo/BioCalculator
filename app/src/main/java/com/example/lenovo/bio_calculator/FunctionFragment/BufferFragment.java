package com.example.lenovo.bio_calculator.FunctionFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.bio_calculator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BufferFragment extends Fragment {


    public BufferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buffer, container, false);

        return view;
    }

}
