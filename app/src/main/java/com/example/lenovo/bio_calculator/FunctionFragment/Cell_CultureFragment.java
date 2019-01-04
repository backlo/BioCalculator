package com.example.lenovo.bio_calculator.FunctionFragment;


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

import com.example.lenovo.bio_calculator.FunctionFragment.CellCultureFragment.CellFirstBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.CellCultureFragment.CellFourthBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.CellCultureFragment.CellSecondBtnFragment;
import com.example.lenovo.bio_calculator.FunctionFragment.CellCultureFragment.CellThirdBtnFragment;
import com.example.lenovo.bio_calculator.MainActivity;
import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cell_CultureFragment extends Fragment {

    @BindView(R.id.cell_first_btn)
    Button first_btn;
    @BindView(R.id.cell_second_btn)
    Button second_btn;
    @BindView(R.id.cell_third_btn)
    Button third_btn;
    @BindView(R.id.cell_fourth_btn)
    Button fourth_btn;

    public Cell_CultureFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cell__culture, container, false);

        ButterKnife.bind(this, view);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(47, 85, 151)));

        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CellFirstBtnFragment());
            }
        });
        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CellSecondBtnFragment());
            }
        });
        third_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CellThirdBtnFragment());
            }
        });
        fourth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CellFourthBtnFragment());
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
