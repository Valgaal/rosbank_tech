package com.example.nikita.rosbank_tech.Persistence;

import android.content.Context;

import com.example.nikita.rosbank_tech.Models.LoginResponse;
import com.example.nikita.rosbank_tech.Models.UserAuth;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import java.util.ArrayList;

import io.reactivex.Single;

public class DataRepository {
    private NetworkUtils networkUtils;
    private RoomDb roomDb;
    private Context mContext;

    public DataRepository(NetworkUtils networkUtils, RoomDb roomDb, Context mContext){
        this.networkUtils = networkUtils;
        this.roomDb = roomDb;
        this.mContext = mContext;
    }

    public Single<UserModel> loginUser(UserAuth userAuth){
        return networkUtils.getWebApi().Auth(userAuth)
                .map(loginResponse -> {
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
}
