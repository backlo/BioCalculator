package com.example.lenovo.bio_calculator.UserFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.bio_calculator.FunctionFragment.MainFragment;
import com.example.lenovo.bio_calculator.R;
import com.example.lenovo.bio_calculator.Util.KakaoSessionCallback;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "LoginFragment";

    private static String CLIENT_ID = "3vot9Yj4Gt7b84REW5sW";
    private static String CLIENT_SECRET = "OPBpR_0C0P";
    private static String CLIENT_NAME = "네이버 아이디로 로그인 테스트";

    @BindView(R.id.login_info)
    TextView login_info;
    @BindView(R.id.kakao_loginbtn)
    LoginButton kakao_loginbtn;
    @BindView(R.id.naver_loginbtn)
    OAuthLoginButton naver_loginbtn;

    @BindView(R.id.naver_getapi)
    Button naver_getapi;
    @BindView(R.id.naver_logout)
    Button naver_logout;

    @BindView(R.id.facebook_loginbtn)
    com.facebook.login.widget.LoginButton facebook_loginbtn;

    //SessionCallback sessionCallback;
    KakaoSessionCallback sessionCallback;
    private KakaoUserInfoListener kakaoUserInfoListener;
    private static OAuthLogin naverLoginInstance;
    private static Context mContext;

    //facebook login
    private CallbackManager mCallbackManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();

        //kakao
        sessionCallback = new KakaoSessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        getUserInfo();

        //naver
        init();
        init_View();

        //facebook
        FacebookSdk.sdkInitialize(getActivity());
        AppEventsLogger.activateApp(getActivity());

        facebook_loginbtn.setReadPermissions(Arrays.asList("public_profile", "email"));
        facebook_loginbtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("facebook2", "");
                    }
                });
                Log.e("facebook2", "" + loginResult.toString());
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,name,email,gender,birthday,picture");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e("facebook2", "onCancel called");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("facebook2", "onError called" + error.toString());
            }
        });

        if(Profile.getCurrentProfile() != null){
            Log.e("facebook","여기!!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
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
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.main_fragment, new MainFragment()).commit();
                }
            }
        });
    }

    //초기화
    private void init() {
        naverLoginInstance = OAuthLogin.getInstance();
        naverLoginInstance.init(mContext, CLIENT_ID, CLIENT_SECRET, CLIENT_NAME);
    }

    //변수 붙이기
    private void init_View() {

        //로그인 핸들러
        @SuppressLint("HandlerLeak")
        OAuthLoginHandler naverLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {//로그인 성공
                    Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show();
                } else {//로그인 실패
                    String errorCode = naverLoginInstance.getLastErrorCode(mContext).getCode();
                    String errorDesc = naverLoginInstance.getLastErrorDesc(mContext);
                    Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
                }
            }

        };

        naver_loginbtn.setOAuthLoginHandler(naverLoginHandler);
        naver_getapi.setOnClickListener(this);
        naver_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.naver_getapi) {
            new RequestApiTask().execute();
        }
        if (v.getId() == R.id.naver_logout) {
            naverLoginInstance.logout(mContext);
            naverLoginInstance.refreshAccessToken(mContext);
        }
    }


    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {//작업이 실행되기 전에 먼저 실행.
            //tv_mail.setText((String) "");//메일 란 비우기
        }

        @Override
        protected String doInBackground(Void... params) {//네트워크에 연결하는 과정이 있으므로 다른 스레드에서 실행되어야 한다.
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = naverLoginInstance.getAccessToken(mContext);
            return naverLoginInstance.requestApi(mContext, at, url);//url, 토큰을 넘겨서 값을 받아온다.json 타입으로 받아진다.
        }

        protected void onPostExecute(String content) {//doInBackground 에서 리턴된 값이 여기로 들어온다.
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONObject response = jsonObject.getJSONObject("response");
                String email = response.getString("email");
                Log.d("naver", "email->>" + email);
                Log.d("naver", "response->>" + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("facebook", "" + Profile.getCurrentProfile().getFirstName() + ",," + Profile.getCurrentProfile().getLastName());
        Log.e("facebook", "onactivityresult");
    }
}
