package com.k3kc.fragmentloginformexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ILogin {
    @GET("users")
    Call<List<LoginModel>> getLoginResult();
}
