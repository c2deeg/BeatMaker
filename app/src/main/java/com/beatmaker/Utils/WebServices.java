package com.beatmaker.Utils;


import com.beatmaker.Handler.SignupHandler;
import com.beatmaker.Handler.SocialLoginHandler;
import com.beatmaker.Models.Login.LoginExample;
import com.beatmaker.Models.Signup.SignupExample;
import com.beatmaker.Models.Socail.SocailLoginExample;
import com.google.gson.JsonObject;
import com.humanresource.Handler.LoginHandler;
import com.humanresource.Utils.SocketConnection;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    private static final String TAG = "WebServices";
    //    public static final String BASE_URL = "http://93.188.167.68:4604/api/";
//    public static final String BASE_URL = "http://13.54.226.124:4604/api/";
    public static final String BASE_URL = "http://93.188.167.68:8070/api/";
    public static final String ImageBaseURL = "http://13.54.226.124/";
    private static Retrofit retrofit = null;
    private static WebServices mInstance;
    private API api;

    public WebServices() {
        mInstance = this;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(API.class);

        }
    }

    public static WebServices getmInstance() {
        return mInstance;
    }

    public void loginMethod(String email, String password, final LoginHandler loginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("deviceToken", password);

        api.loginAPI(jsonObject).enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {

                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.Companion.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        loginHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                loginHandler.onError(t.getMessage());
            }
        });
    }
    public void signupMethod(String name, String email,String phoneNo, String password,String deviceToken, final SignupHandler signupHandler ) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("phoneNo", phoneNo);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("deviceToken", deviceToken);

        api.registerAPI(jsonObject).enqueue(new Callback<SignupExample>() {
            @Override
            public void onResponse(Call<SignupExample> call, Response<SignupExample> response) {

                if (response.code() == 200) {
                    signupHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.Companion.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        signupHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupExample> call, Throwable t) {
                signupHandler.onError(t.getMessage());
            }
        });
    }
    public void socialLogin(String socialLoginId, String platform,String name,  String gender, final SocialLoginHandler socialLoginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "name");

        api.socialloginAPI(jsonObject).enqueue(new Callback<SocailLoginExample>() {
            @Override
            public void onResponse(Call<SocailLoginExample> call, Response<SocailLoginExample> response) {

                if (response.code() == 200) {
                    socialLoginHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.Companion.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        socialLoginHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SocailLoginExample> call, Throwable t) {
                socialLoginHandler.onError(t.getMessage());
            }
        });
    }




}
