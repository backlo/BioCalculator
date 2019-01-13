package com.example.lenovo.bio_calculator.UserFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.bio_calculator.FunctionFragment.MainFragment;
import com.example.lenovo.bio_calculator.R;
import com.example.lenovo.bio_calculator.Util.KakaoSessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment{
    public static final String TAG = "LoginFragment";

    @BindView(R.id.kakao_loginbtn)
    LoginButton kakao_loginbtn;

    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.find_login_btn)
    Button find_login_btn;
    @BindView(R.id.login_edittext)
    EditText login_edittext;
    @BindView(R.id.pass_edittext)
    EditText pass_edittext;

    SharedPreferences pref;

    //SessionCallback sessionCallback;
    KakaoSessionCallback sessionCallback;
    private KakaoUserInfoListener kakaoUserInfoListener;
    private static Context mContext;

    public LoginFragment() {  }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //kakao
        sessionCallback = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        getUserInfo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();

        pref = this.getActivity().getSharedPreferences("lan",Context.MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")){
            login_btn.setText(getString(R.string.main_login_text_kor));
            find_login_btn.setText(getString(R.string.main_findlogin_text_kor));
            login_edittext.setHint(getString(R.string.main_login_text_kor));
            pass_edittext.setHint(getString(R.string.main_password_text_kor));
        } else{
            //영어버전
        }

        return view;
    }

    //kakao login
    public void getUserInfo() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e(TAG, "error message=" + errorResult);
                //super.onFailure(errorResult);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d(TAG, "onSessionClosed1" + errorResult);
            }

            @Override
            public void onNotSignedUp() {
                //카카오톡 회원이 아닐시
                Log.d(TAG, "onNotSignedUp ");
            }

            @Override
            public void onSuccess(UserProfile result) {
                Log.e(TAG, "onsucess1" + result.toString());
                Log.e(TAG, "onsucess1" + result.getId() + "");
                //카톡 로그인세션 살아있을때 해당 유저이름가져옴
                kakaoUserInfoListener.userNickname(result.getNickname());
                if (getActivity().getFragmentManager() != null) {
                    Log.e(TAG,"null아님");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new MainFragment()).commit();
                }
            }
        });
    }


    //kakao login
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof KakaoUserInfoListener) {
            kakaoUserInfoListener = (KakaoUserInfoListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    //kakao login
    @Override
    public void onDetach() {
        super.onDetach();
        kakaoUserInfoListener = null;
    }

    public interface KakaoUserInfoListener {
        void userNickname(String userNickname);
    }

}
