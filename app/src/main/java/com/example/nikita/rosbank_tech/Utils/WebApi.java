package com.example.nikita.rosbank_tech.Utils;

import com.example.nikita.rosbank_tech.Models.LoginResponse;
import com.example.nikita.rosbank_tech.Models.UserAuth;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebApi {

    @POST("/api/auth/signin")
    Single<LoginResponse> Auth(@Body UserAuth userAuth);
}
