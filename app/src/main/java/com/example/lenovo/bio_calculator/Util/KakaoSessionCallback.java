package com.example.lenovo.bio_calculator.Util;

import android.util.Log;

import com.example.lenovo.bio_calculator.Event;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.greenrobot.eventbus.EventBus;

public class KakaoSessionCallback implements ISessionCallback {

    @Override
    public void onSessionOpened() {

        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    //에러로 인한 로그인 실패
                    // finish();
                } else {
                    //redirectMainActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Log.e("kakao at session", userProfile.toString());
                Log.e("kakao at session", userProfile.getId() + "");
                EventBus.getDefault().post(new Event(2,userProfile.getNickname()));
            }
        });

    }

    // 세션 실패시
    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e("kakao at session","session open failed");
    }
}

