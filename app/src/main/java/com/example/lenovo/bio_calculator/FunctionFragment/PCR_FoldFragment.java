package com.example.lenovo.bio_calculator.FunctionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.grantland.widget.AutofitTextView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class PCR_FoldFragment extends Fragment {

    @BindView(R.id.edit_A)
    EditText edit_A;
    @BindView(R.id.edit_B)
    EditText edit_B;
    @BindView(R.id.edit_C)
    EditText edit_C;
    @BindView(R.id.edit_D)
    EditText edit_D;
    @BindView(R.id.edit_E)
    EditText edit_E;
    @BindView(R.id.edit_F)
    EditText edit_F;
    @BindView(R.id.edit_G)
    EditText edit_G;
    @BindView(R.id.edit_H)
    EditText edit_H;
    @BindView(R.id.edit_I)
    EditText edit_I;
    @BindView(R.id.edit_J)
    EditText edit_J;
    @BindView(R.id.edit_K)
    EditText edit_K;
    @BindView(R.id.edit_L)
    EditText edit_L;

    @BindView(R.id.pcr_mainview)
    LinearLayout pcr_mainview;

    @BindView(R.id.result_S)
    AutofitTextView result_S;
    @BindView(R.id.result_T)
    TextView result_T;
    @BindView(R.id.delete_all_btn)
    Button delete_all_btn;
    @BindView(R.id.cal_btn)
    Button cal_btn;
    @BindView(R.id.result_explain)
    AutofitTextView result_explain;

    public PCR_FoldFragment() {
    }

    double getedit_a;
    double getedit_b;
    double getedit_c;
    double getedit_d;
    double getedit_e;
    double getedit_f;
    double getedit_g;
    double getedit_h;
    double getedit_i;
    double getedit_j;
    double getedit_k;
    double getedit_l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            /*
            계산식
            (S) ΔΔCt = ((D+E+F/3)-(J+K+L /3)) – ((A+B+C/ 3)-(G+H+I/3))
            (T) Fold change 값 =2-((D+E+F/3)-(J+K+L /3)) – ((A+B+C/ 3)-(G+H+I/3))
             */
        View view = inflater.inflate(R.layout.fragment_pcr__fold, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.cal_btn)
    public void getResult(){

        if(edit_A.getText() != null && edit_B.getText() != null && edit_C.getText() != null && edit_D.getText() != null
                && edit_E.getText() != null && edit_F.getText() != null && edit_G.getText() != null && edit_H.getText() !=null
                && edit_I.getText() != null && edit_J.getText() != null && edit_K.getText() !=null && edit_L.getText() != null){

            getedit_a = Double.parseDouble(edit_A.getText().toString());
            getedit_b = Double.parseDouble(edit_B.getText().toString());
            getedit_c = Double.parseDouble(edit_C.getText().toString());
            getedit_d = Double.parseDouble(edit_D.getText().toString());
            getedit_e = Double.parseDouble(edit_E.getText().toString());
            getedit_f = Double.parseDouble(edit_F.getText().toString());
            getedit_g = Double.parseDouble(edit_G.getText().toString());
            getedit_h = Double.parseDouble(edit_H.getText().toString());
            getedit_i = Double.parseDouble(edit_I.getText().toString());
            getedit_j = Double.parseDouble(edit_J.getText().toString());
            getedit_k = Double.parseDouble(edit_K.getText().toString());
            getedit_l = Double.parseDouble(edit_L.getText().toString());

            double result_s;
            result_s = (((getedit_d+getedit_e+getedit_f)/3) - ((getedit_j+getedit_k+getedit_l) / 3)) -
                    (((getedit_a+getedit_b+getedit_c)/3) - ((getedit_g+getedit_h+getedit_i)/3));
            result_S.setText(String.valueOf(result_s));

            double result_t;
            result_t = 2 - ((getedit_d+getedit_e+getedit_f)/3) - ((getedit_a+getedit_b+getedit_c)/3) - ((getedit_g+getedit_h+getedit_i)/3);
            result_T.setText(String.valueOf(result_t));

            result_explain.setText("Control 군과 실험군의 타겟 유전자 발현량은 ("+ result_t + ") 배 차이가 난다.");

        }else{
            Toast.makeText(getActivity(), "데이터를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.delete_all_btn)
    public void deleteAll(){
        edit_A.setText(null);
        edit_B.setText(null);
        edit_C.setText(null);
        edit_D.setText(null);
        edit_E.setText(null);
        edit_F.setText(null);
        edit_G.setText(null);
        edit_H.setText(null);
        edit_I.setText(null);
        edit_J.setText(null);
        edit_K.setText(null);
        edit_L.setText(null);
        result_T.setText(null);
        result_S.setText(null);
        result_explain.setText(null);
    }

    @OnClick(R.id.pcr_mainview)
    public void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(pcr_mainview.getWindowToken(), 0 );
    }

}
