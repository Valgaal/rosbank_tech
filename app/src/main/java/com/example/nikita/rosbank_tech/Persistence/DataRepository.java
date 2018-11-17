package com.example.nikita.rosbank_tech.Persistence;

import com.example.nikita.rosbank_tech.Models.LoginResponse;
import com.example.nikita.rosbank_tech.Models.UserAuth;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;

import io.reactivex.Single;

public class DataRepository {
    private NetworkUtils networkUtils;
    private RoomDb roomDb;

    public DataRepository(NetworkUtils networkUtils, RoomDb roomDb){
        this.networkUtils = networkUtils;
        this.roomDb = roomDb;
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
        userModel.setId(loginResponse.getUserProfileDTO().getId());
        userModel.setBalance(loginResponse.getUserProfileDTO().getBalance());
        userModel.setCardNumber(loginResponse.getUserProfileDTO().getCardNumber());
        userModel.setName(loginResponse.getUserProfileDTO().getName());
        return userModel;
    }
}
