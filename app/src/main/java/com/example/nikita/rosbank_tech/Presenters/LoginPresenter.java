package com.example.nikita.rosbank_tech.Presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.nikita.rosbank_tech.Models.UserAuth;
import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Persistence.UserModel;
import com.example.nikita.rosbank_tech.UI.App;
import com.example.nikita.rosbank_tech.Utils.NetworkUtils;
import com.example.nikita.rosbank_tech.views.LoginView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    public NetworkUtils networkUtils;

    @Inject
    public DataRepository dataRepository;

    CompositeDisposable disposable = new CompositeDisposable();

    public LoginPresenter(){
        App.getComponent().inject(this);
    }

    public void buttonClicked(String email, String password){
        getViewState().showLoading();
        UserAuth userAuth = new UserAuth();
        userAuth.setLogin(email);
        userAuth.setPassword(password);
        disposable.add(dataRepository.loginUser(userAuth)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                                   @Override
                                   public void onSuccess(UserModel userModel) {
                                       getViewState().finishLoading();
                                       getViewState().login();
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       getViewState().finishLoading();
                                       getViewState().showError(e.toString());
                                   }
                               }));
    }
}
