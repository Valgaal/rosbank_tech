package com.example.nikita.rosbank_tech.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface LoginView extends MvpView {
    void login();
    void showLoading();
    void finishLoading();
}
