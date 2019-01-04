package com.example.lenovo.bio_calculator.FunctionFragment.MakeTabFragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.MainActivity;
import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeFirstBtnFragment extends Fragment {

    @BindView(R.id.make_first_a)
    EditText ed_a;
    @BindView(R.id.make_first_b)
    EditText ed_b;
    @BindView(R.id.make_first_c)
    EditText ed_c;
    @BindView(R.id.make_first_d)
    TextView ed_d;
    @BindView(R.id.make_fisrt_water_unit)
    Spinner water_unit;
    @BindView(R.id.make_first_volume_unit)
    Spinner volume_unit;
    @BindView(R.id.make_first_cal_unit)
    TextView cal_unit;
    @BindView(R.id.make_first_delete)
    Button del_btn;
    @BindView(R.id.make_first_cal)
    Button cal_btn;
    @BindView(R.id.sol_b)
    TextView sol_b;
    @BindView(R.id.sol_c)
    TextView sol_c;
    @BindView(R.id.sol_d)
    TextView sol_d;
    @BindView(R.id.sol_show)
    LinearLayout sol_show;

    static String w_unit, v_unit, c_unit;
    double a,b,c,d;


    public MakeFirstBtnFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_first_btn, container, false);
        ButterKnife.bind(this, view);

        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(47,85,151)));

        water_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                w_unit = parent.getItemAtPosition(position).toString();         }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        volume_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                v_unit = parent.getItemAtPosition(position).toString();         }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed_a.getText().toString().equals("") || ed_b.getText().toString().equals("") || ed_c.getText().toString().equals("")){
                    Toast.makeText(getContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                } else{
                    a = Double.parseDouble(ed_a.getText().toString());
                    b = Double.parseDouble(ed_b.getText().toString());
                    c = Double.parseDouble(ed_c.getText().toString());

                    d = makeFirstCal(a,b,c,w_unit,v_unit);

                    if(d < 0.001 && d >= 0.0000001) {
                        d = d*1000;
                        ed_d.setText(d+"");
                        cal_unit.setText("mg");
                        c_unit = "mg";
                    } else if(d < 0.0000001 && d != 0) {
                        d = d*1000000;
                        ed_d.setText(d+"");
                        cal_unit.setText("ug");
                        c_unit = "ug";
                    } else if(d == 0){
                        ed_d.setText(d+"");
                        cal_unit.setText("g");
                        c_unit = "g";
                    } else {
                        ed_d.setText(d+"");
                        cal_unit.setText("g");
                        c_unit = "g";
                    }

                    sol_show.setVisibility(View.VISIBLE);

                    sol_b.setText(b+w_unit+"");
                    sol_c.setText(c+v_unit+"");
                    sol_d.setText(d+c_unit+"");
                }
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_a.setText("");
                ed_b.setText("");
                ed_c.setText("");
                ed_d.setText("");
                cal_unit.setText("");
                sol_show.setVisibility(View.GONE);
            }
        });

        return view;
    }

    public double makeFirstCal(double a, double b, double c, String w_unit, String v_unit){

        if(w_unit.equals("mM")) {
            b = b*0.001;
        } else if(w_unit.equals("uM")) {
            b = b*0.000001;
        }

        if(v_unit.equals("mL")){
            c = c*0.001;
        } else if(v_unit.equals("uL")){
            c = c*0.000001;
        }
        return a*b*c;
    }

}
