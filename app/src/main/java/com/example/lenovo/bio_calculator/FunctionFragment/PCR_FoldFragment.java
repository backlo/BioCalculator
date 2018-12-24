package com.example.lenovo.bio_calculator.FunctionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.bio_calculator.R;

public class PCR_FoldFragment extends Fragment {
    public PCR_FoldFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pcr__fold, container, false);
    }

}
