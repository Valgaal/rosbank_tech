package com.example.nikita.rosbank_tech.Utils;

import com.example.nikita.rosbank_tech.Models.LoginResponse;
import com.example.nikita.rosbank_tech.Models.OperationsResponseDTO;
import com.example.nikita.rosbank_tech.Models.PaymentResponse;
import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.Models.UserAuth;
import com.example.nikita.rosbank_tech.Models.UserPrefs;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebApi {

    @POST("/api/auth/signin")
    Single<LoginResponse> Auth(@Body UserAuth userAuth);

    @POST("/api/products/okvdandmain")
    Call<List<ProductModel>> findProducts(@Header("Authorization") String token, @Body UserPrefs userPrefs);

    @GET("api/user/operations")
    Single<List<OperationsResponseDTO>> getPayment(@Header("Authorization") String token);

    @GET("api/user/me")
    Single<UserModel> getActualInfo(@Header("Authorization") String token);
}
