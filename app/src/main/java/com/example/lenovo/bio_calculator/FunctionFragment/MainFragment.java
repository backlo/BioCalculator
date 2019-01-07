package com.example.lenovo.bio_calculator.FunctionFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.bio_calculator.MainActivity;
import com.example.lenovo.bio_calculator.R;
import com.example.lenovo.bio_calculator.SplashActivity;

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

    @BindView(R.id.main_make_text)
    TextView main_make_text;
    @BindView(R.id.main_dilution_text)
    TextView main_dilution_text;
    @BindView(R.id.main_mw_text)
    TextView main_mw_text;
    @BindView(R.id.main_cell_text)
    TextView main_cell_text;
    @BindView(R.id.main_buffer_text)
    TextView main_buffer_text;
    @BindView(R.id.main_pcr_text)
    TextView main_pcr_text;
    @BindView(R.id.main_sds_text)
    TextView main_sds_text;
    @BindView(R.id.main_qa_text)
    TextView main_qa_text;

    SharedPreferences pref;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        pref = this.getActivity().getSharedPreferences("lan", Context.MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")){
            main_make_text.setText(getString(R.string.main_fragment_make_text_kor));
            main_dilution_text.setText(getString(R.string.main_fragment_dil_text_kor));
            main_mw_text.setText(getString(R.string.main_fragment_mw_text_kor));
            main_cell_text.setText(getString(R.string.main_fragment_cell_text_kor));
            main_buffer_text.setText(getString(R.string.main_fragment_buffer_text_kor));
            main_pcr_text.setText(getString(R.string.main_fragment_pcr_text_kor));
            main_sds_text.setText(getString(R.string.main_fragment_sds_text_kor));
            main_qa_text.setText(getString(R.string.main_fragment_qa_text_kor));
        }else {
            //영어버전
        }


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

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(237,125,49)));
    }



}
