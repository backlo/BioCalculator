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
import android.widget.Button;

import com.example.lenovo.bio_calculator.FunctionFragment.MakeTabFragment.MakeFirstBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.MakeTabFragment.MakeFourthBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.MakeTabFragment.MakeSecondBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.MakeTabFragment.MakeThirdBtnFragment;
import com.example.lenovo.bio_calculator.MainActivity;
import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeFragment extends Fragment{

    @BindView(R.id.make_first_btn)
    Button first_btn;
    @BindView(R.id.make_second_btn)
    Button second_btn;
    @BindView(R.id.make_third_btn)
    Button third_btn;
    @BindView(R.id.make_fourth_btn)
    Button fourth_btn;

    SharedPreferences pref;

    public MakeFragment() {    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make, container, false);

        ButterKnife.bind(this, view);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(47, 85, 151)));

        pref = this.getActivity().getSharedPreferences("lan",Context.MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")){
            first_btn.setText(getString(R.string.make_fragment_firstbtn_text_kor));
            second_btn.setText(getString(R.string.make_fragment_secondbtn_text_kor));
            third_btn.setText(getString(R.string.make_fragment_thirdbtn_text_kor));
            fourth_btn.setText(getString(R.string.make_fragment_fourthbtn_text_kor));
        } else{
            //영어버전
        }

        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MakeFirstBtnFragment());
            }
        });
        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MakeSecondBtnFragment());
            }
        });
        third_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MakeThirdBtnFragment());
            }
        });
        fourth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MakeFourthBtnFragment());
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
