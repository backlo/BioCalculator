package com.example.lenovo.bio_calculator.Util.Facebook;

public class FacebookLoginCallback{
//    @Override
//    public void onSuccess(LoginResult loginResult) {
//        //페북 로그인성공
//        Log.e("facebook", "onSuccess"+loginResult.toString());
//        requestMe(loginResult.getAccessToken());
//    }
//
//    @Override
//    public void onCancel() {
//        //페북 로그인 취소할때
//        Log.e("facebook", "onCancel");
//
//    }
//
//    @Override
//    public void onError(FacebookException error) {
//        //페북 로그인 실패
//        Log.e("facebook", "onError"+error.toString());
//    }
//
//    // 사용자 정보 요청
//    public void requestMe(AccessToken token) {
//        GraphRequest graphRequest = GraphRequest.newMeRequest(token,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("result",object.toString());
//                    }
//                });
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,gender,birthday");
//        graphRequest.setParameters(parameters);
//        graphRequest.executeAsync();
//    }
}
