package com.example.nikita.rosbank_tech.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface LoginView extends MvpView {
    void login(UserModel userModel);
    void showLoading();
    void finishLoading();
    void showError(String error);
}
