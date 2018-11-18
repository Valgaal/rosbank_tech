package com.example.nikita.rosbank_tech.Persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikita.rosbank_tech.Models.LoginResponse;
import com.example.nikita.rosbank_tech.Models.OperationsResponseDTO;
import com.example.nikita.rosbank_tech.Models.PaymentRequest;
import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.Models.ResponseBuy;
import com.example.nikita.rosbank_tech.Models.UserAuth;
import com.example.nikita.rosbank_tech.Models.UserPrefs;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class DataRepository {
    private NetworkUtils networkUtils;
    private RoomDb roomDb;
    private Context mContext;
    private SharedPreferences sharedPreferences;

    private static final String TOKEN = "Token";

    public DataRepository(NetworkUtils networkUtils, RoomDb roomDb, Context mContext, SharedPreferences sharedPreferences){
        this.networkUtils = networkUtils;
        this.roomDb = roomDb;
        this.mContext = mContext;
        this.sharedPreferences = sharedPreferences;
    }

    public Single<UserModel> loginUser(UserAuth userAuth){
        return networkUtils.getWebApi().Auth(userAuth)
                .map(loginResponse -> {
                            sharedPreferences.edit().putString(TOKEN, loginResponse.getAccessToken()).apply();
                            UserModel userModel = convertToUserModel(loginResponse);
                            roomDb.userDao().insert(userModel);
                            return userModel;
                        }
                );
    }

    private UserModel convertToUserModel(LoginResponse loginResponse){
        UserModel userModel = new UserModel();
        ArrayList<String> okveds = new ArrayList<>();
        okveds.add("47.19");
        okveds.add("47.73");
        okveds.add("47.75");
        userModel.setOkved(okveds);
        userModel.setWork(mContext.getResources().getString(R.string.work_name));
        userModel.setId(loginResponse.getUserProfileDTO().getId());
        userModel.setBalance(loginResponse.getUserProfileDTO().getBalance());
        userModel.setCardNumber(loginResponse.getUserProfileDTO().getCardNumber());
        userModel.setName(loginResponse.getUserProfileDTO().getName());
        return userModel;
    }

    public void updateUserModel(UserModel userModel){
        Completable.fromAction(() -> roomDb.userDao().update(userModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public Single<UserModel> getActualInfo(){
        return networkUtils.getWebApi().getActualInfo(sharedPreferences.getString(TOKEN, ""));
    }

    public Call<List<ProductModel>> findProducts(UserModel userModel){
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMainCategory(userModel.getWork());
        userPrefs.setOkvds(userModel.getOkved());

        return networkUtils.getWebApi().findProducts(sharedPreferences.getString(TOKEN, ""),userPrefs);
    }

    public Single<List<OperationsResponseDTO>> getPayment(){
        return networkUtils.getWebApi().getPayment(sharedPreferences.getString(TOKEN, ""));
    }

    public Single<ResponseBuy> buyProduct(PaymentRequest paymentRequest){
        return networkUtils.getWebApi().buyProduct(sharedPreferences.getString(TOKEN, ""), paymentRequest);
    }
}
