package com.beatmaker.Utils;


import com.beatmaker.Models.Login.LoginExample;
import com.beatmaker.Models.Signup.SignupExample;
import com.beatmaker.Models.Socail.SocailLoginExample;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    //////users////////

//    @FormUrlEncoded
    @POST("users/login")
    Call<LoginExample> loginAPI(@Body JsonObject jsonObject);

//
    @POST("users/create")
    Call<SignupExample> registerAPI(@Body JsonObject jsonObject);

    @POST("users/socialLogin")
    Call<SocailLoginExample>socialloginAPI(@Body JsonObject jsonObject);

////    @FormUrlEncoded
//    @POST("users/register")
//    Call<SignUpExample> registerAPI(@Field("fullname") String fullname, @Field("email") String email, @Field("password") String password);


//    @FormUrlEncoded
//    @POST("users/forgotPassword")
//    Call<ForgotPassExample> forgotPass(@Field("email") String email);
//
//    @FormUrlEncoded
//    @POST("users/otpVerifyAndChangePassword")
//    Call<VerifyOTPExample> verifyOTPApi(@Header("x-access-token") String token, @Field("otp") String otp);
//
//    @FormUrlEncoded
//    @PUT("users/newPassword")
//    Call<ResetPaswordExample> resetPasswordAPI(@Header("x-access-token") String token, @Field("newPassword") String newPassword);

//    @PUT("users/changePassword/{id}")
//    Call<ChangePassExample> changePasswordAPI(@Header("x-access-token") String token, @Header("Content-Type") String content_type, @Path("id") String userId, @Body JsonObject jsonObject);

}