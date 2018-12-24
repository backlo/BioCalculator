package com.example.lenovo.bio_calculator.FunctionFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.make)
    LinearLayout make;
    @BindView(R.id.dilution)
    LinearLayout dilution;
    @BindView(R.id.mw_cal)
    LinearLayout mw_cal;
    @BindView(R.id.cell_culture)
    LinearLayout cell_culture;
    @BindView(R.id.buffer)
    LinearLayout buffer;
    @BindView(R.id.pcr_fold)
    FrameLayout pcr_fold;
    @BindView(R.id.gel_cal)
    LinearLayout gel_cal;
    @BindView(R.id.q_and_a)
    LinearLayout q_and_a;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        make.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                replaceFragment(new MakeFragment());
            }
        });
        dilution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { replaceFragment(new DilutionFragment());
            }
        });
        mw_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { replaceFragment(new MW_CalFragment());
            }
        });
        cell_culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { replaceFragment(new Cell_CultureFragment());
            }
        });
        buffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                replaceFragment(new BufferFragment());
            }
        });
        pcr_fold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                replaceFragment(new PCR_FoldFragment());
            }
        });
        gel_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                replaceFragment(new Gel_CalFragment());
            }
        });
        q_and_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                replaceFragment(new Q_AFragment());
            }
        });

        return view;
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
    }

}
