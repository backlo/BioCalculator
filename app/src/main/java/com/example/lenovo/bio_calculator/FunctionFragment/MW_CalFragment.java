package com.example.lenovo.bio_calculator.FunctionFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MW_CalFragment extends Fragment implements Button.OnClickListener {

    @BindView(R.id.text_element)
    TextView text_element;
    @BindView(R.id.text_molecular)
    TextView text_molecular;

    @BindView(R.id.mw_del_btn) Button mw_del_btn;
    @BindView(R.id.mw_delall_btn) Button mw_delall_btn;
    @BindView(R.id.mw_calculator) Button mw_calculator;
    @BindView(R.id.mw_copy) Button mw_copy;

    @BindView(R.id.mw_0) Button mw_0;
    @BindView(R.id.mw_1) Button mw_1;
    @BindView(R.id.mw_2) Button mw_2;
    @BindView(R.id.mw_3) Button mw_3;
    @BindView(R.id.mw_4) Button mw_4;
    @BindView(R.id.mw_5) Button mw_5;
    @BindView(R.id.mw_6) Button mw_6;
    @BindView(R.id.mw_7) Button mw_7;
    @BindView(R.id.mw_8) Button mw_8;
    @BindView(R.id.mw_9) Button mw_9;
    @BindView(R.id.mw_open) Button mw_open;
    @BindView(R.id.mw_close) Button mw_close;

    @BindView(R.id.mw_H) Button mw_H;
    @BindView(R.id.mw_C) Button mw_C;
    @BindView(R.id.mw_O) Button mw_O;
    @BindView(R.id.mw_N) Button mw_N;
    @BindView(R.id.mw_P) Button mw_P;

    @BindView(R.id.mw_Li) Button mw_Li;
    @BindView(R.id.mw_Na) Button mw_Na;
    @BindView(R.id.mw_K) Button mw_K;
    @BindView(R.id.mw_Cs) Button mw_Cs;
    @BindView(R.id.mw_Ag) Button mw_Ag;

    @BindView(R.id.mw_Ca) Button mw_Ca;
    @BindView(R.id.mw_Mg) Button mw_Mg;
    @BindView(R.id.mw_Ba) Button mw_Ba;
    @BindView(R.id.mw_B) Button mw_B;
    @BindView(R.id.mw_Al) Button mw_Al;

    @BindView(R.id.mw_Cr) Button mw_Cr;
    @BindView(R.id.mw_Mn) Button mw_Mn;
    @BindView(R.id.mw_Fe) Button mw_Fe;
    @BindView(R.id.mw_Co) Button mw_Co;
    @BindView(R.id.mw_Ni) Button mw_Ni;

    @BindView(R.id.mw_Cl) Button mw_Cl;
    @BindView(R.id.mw_F) Button mw_F;
    @BindView(R.id.mw_Br) Button mw_Br;
    @BindView(R.id.mw_I) Button mw_I;
    @BindView(R.id.mw_S) Button mw_S;

    @BindView(R.id.mw_H2O) Button mw_H2O;
    @BindView(R.id.mw_CO3) Button mw_CO3;
    @BindView(R.id.mw_PO4) Button mw_PO4;
    @BindView(R.id.mw_SO4) Button mw_SO4;
    @BindView(R.id.mw_NH4) Button mw_NH4;

    @BindView(R.id.mw_HPO3) Button mw_HPO3;
    @BindView(R.id.mw_SO3) Button mw_SO3;
    @BindView(R.id.mw_NO3) Button mw_NO3;
    @BindView(R.id.mw_OH) Button mw_OH;
    @BindView(R.id.mw_HPO4) Button mw_HPO4;

    String sik="";
    double cal=0;

    public MW_CalFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mw__cal, container, false);
        ButterKnife.bind(this, view);

        mw_del_btn.setOnClickListener(this);
        mw_delall_btn.setOnClickListener(this);
        mw_calculator.setOnClickListener(this);
        mw_copy.setOnClickListener(this);

        mw_0.setOnClickListener(this);
        mw_1.setOnClickListener(this);
        mw_2.setOnClickListener(this);
        mw_3.setOnClickListener(this);
        mw_4.setOnClickListener(this);
        mw_5.setOnClickListener(this);
        mw_6.setOnClickListener(this);
        mw_7.setOnClickListener(this);
        mw_8.setOnClickListener(this);
        mw_9.setOnClickListener(this);
        mw_open.setOnClickListener(this);
        mw_close.setOnClickListener(this);

        mw_H.setOnClickListener(this);
        mw_C.setOnClickListener(this);
        mw_O.setOnClickListener(this);
        mw_N.setOnClickListener(this);
        mw_P.setOnClickListener(this);

        mw_Li.setOnClickListener(this);
        mw_Na.setOnClickListener(this);
        mw_K.setOnClickListener(this);
        mw_Cs.setOnClickListener(this);
        mw_Ag.setOnClickListener(this);

        mw_Ca.setOnClickListener(this);
        mw_Mg.setOnClickListener(this);
        mw_Ba.setOnClickListener(this);
        mw_B.setOnClickListener(this);
        mw_Al.setOnClickListener(this);

        mw_Cr.setOnClickListener(this);
        mw_Mn.setOnClickListener(this);
        mw_Fe.setOnClickListener(this);
        mw_Co.setOnClickListener(this);
        mw_Ni.setOnClickListener(this);

        mw_Cl.setOnClickListener(this);
        mw_F.setOnClickListener(this);
        mw_Br.setOnClickListener(this);
        mw_I.setOnClickListener(this);
        mw_S.setOnClickListener(this);

        mw_H2O.setOnClickListener(this);
        mw_CO3.setOnClickListener(this);
        mw_PO4.setOnClickListener(this);
        mw_SO4.setOnClickListener(this);
        mw_NH4.setOnClickListener(this);

        mw_HPO3.setOnClickListener(this);
        mw_SO3.setOnClickListener(this);
        mw_NO3.setOnClickListener(this);
        mw_OH.setOnClickListener(this);
        mw_HPO4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mw_del_btn:
                if(text_element.getText().equals("")) {
                    Toast.makeText(getContext(), "값이 없습니다", Toast.LENGTH_SHORT).show();
                    break;
                } else{
                    String text = text_element.getText().toString();
                    break;
                }

            case R.id.mw_delall_btn:
                text_molecular.setText("");
                text_element.setText("");
                sik="";
                break;
            case R.id.mw_calculator:
                text_molecular.setText(cal+"");
                break;
            case R.id.mw_copy:
                break;

            case R.id.mw_0:
                break;
            case R.id.mw_1:
                break;
            case R.id.mw_2:
                break;
            case R.id.mw_3:
                break;
            case R.id.mw_4:
                break;
            case R.id.mw_5:
                break;
            case R.id.mw_6:
                break;
            case R.id.mw_7:
                break;
            case R.id.mw_8:
                break;
            case R.id.mw_9:
                break;
            case R.id.mw_open:
                break;
            case R.id.mw_close:
                break;

            case R.id.mw_H:
                break;
            case R.id.mw_C:
                break;
            case R.id.mw_O:
                break;
            case R.id.mw_N:
                break;
            case R.id.mw_P:
                break;

            case R.id.mw_Li:
                break;
            case R.id.mw_Na:
                break;
            case R.id.mw_K:
                break;
            case R.id.mw_Cs:
                break;
            case R.id.mw_Ag:
                break;

            case R.id.mw_Ca:
                break;
            case R.id.mw_Mg:
                break;
            case R.id.mw_Ba:
                break;
            case R.id.mw_B:
                break;
            case R.id.mw_Al:
                break;

            case R.id.mw_Cr:
                break;
            case R.id.mw_Mn:
                break;
            case R.id.mw_Fe:
                break;
            case R.id.mw_Co:
                break;
            case R.id.mw_Ni:
                break;

            case R.id.mw_Cl:
                break;
            case R.id.mw_F:
                break;
            case R.id.mw_Br:
                break;
            case R.id.mw_I:
                break;
            case R.id.mw_S:
                break;

            case R.id.mw_H2O:
                break;
            case R.id.mw_CO3:
                break;
            case R.id.mw_PO4:
                break;
            case R.id.mw_SO4:
                break;
            case R.id.mw_NH4:
                break;

            case R.id.mw_HPO3:
                break;
            case R.id.mw_SO3:
                break;
            case R.id.mw_NO3:
                break;
            case R.id.mw_OH:
                break;
            case R.id.mw_HPO4:
                break;

        }
    }
}
