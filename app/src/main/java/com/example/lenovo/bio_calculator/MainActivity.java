package com.example.lenovo.bio_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.FunctionFragment.MainFragment;
import com.example.lenovo.bio_calculator.UserFragment.LoginFragment;
import com.facebook.CallbackManager;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoginFragment.KakaoUserInfoListener {

    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_sign)
    Button loginSign;
    @BindView(R.id.login_userinfo)
    TextView login_userinfo;

    String usernickName;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //키값 알아내는법
        /*try{
            PackageInfo info = getPackageManager().getPackageInfo("com.example.lenovo.bio_calculator",PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash: ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/

        //이벤트버스 받는부분
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) { }

        ButterKnife.bind(this);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
        }

        kakaoLoginChecker();
        pref = getSharedPreferences("lan",MODE_PRIVATE);

        if(pref.getString("lan","").equals("kor")){
            login.setText(getString(R.string.main_login_text_kor));
            loginSign.setText(getString(R.string.main_sign_text_kor));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new MainFragment()).commit();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                replaceFragment(new LoginFragment());
            }
        });
        loginSign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { Toast.makeText(getApplicationContext(),"회원가입 탭",Toast.LENGTH_SHORT).show(); }
        });

    }

    private void replaceFragment(Fragment fragment){
        String fragmentTag = fragment.getClass().getSimpleName();
        getSupportFragmentManager().popBackStack(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).addToBackStack(fragmentTag).commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        pref = getSharedPreferences("lan", MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")){
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        } else{
            // 영문
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "검색 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_logout) {
            if (Session.getCurrentSession().isOpened()) {
                Session.getCurrentSession().close();
                kakaoLogout();
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "로그인 먼저 해주세요!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if (id == R.id.action_share) {
            Toast.makeText(this, "공유 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "about 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void kakaoLogout() {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                EventBus.getDefault().post(new Event(1));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void kakaoLoginEvent(Event event) {
        if(event.data == 1){
            login_userinfo.setText("");
            login.setVisibility(View.VISIBLE);
            loginSign.setVisibility(View.VISIBLE);
        } else if(event.data == 2){
            login.setVisibility(View.GONE);
            loginSign.setVisibility(View.GONE);
            pref = getSharedPreferences("lan", MODE_PRIVATE);
            if(pref.getString("lan","").equals("kor")) {
                login_userinfo.setText(event.nickname + "님 환영합니다.");
            } else{
                //영문
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new MainFragment()).commit();
        }
    }

    public void kakaoLoginChecker() {
        if (Session.getCurrentSession().isOpened()) {
            Log.e("kakao", "로그인되어잇음");
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.e("kakao", "실패");
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile result) {
                    pref = getSharedPreferences("lan", MODE_PRIVATE);
                    if(pref.getString("lan","").equals("kor")) {
                        login_userinfo.setText(result.getNickname() + "님 환영합니다.");
                    } else{
                        //영문
                    }
                    login.setVisibility(View.GONE);
                    loginSign.setVisibility(View.GONE);
                }
            });
        } else {
            login_userinfo.setText("");
        }
    }

    @Override
    public void userNickname(String userNickname) {
        usernickName = userNickname;
        pref = getSharedPreferences("lan", MODE_PRIVATE);
        if(pref.getString("lan","").equals("kor")) {
            login_userinfo.setText(usernickName + "님 환영합니다.");
        } else{
            //영문
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CallbackManager mCallbackManager = new CallbackManager() {
            @Override
            public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                return false;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {        }
        pref = getSharedPreferences("lan", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("lan");
        editor.commit();
    }

}
