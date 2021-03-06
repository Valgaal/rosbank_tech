package com.example.nikita.rosbank_tech.UI.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.nikita.rosbank_tech.Adapters.ChangesAdapter;
import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.UI.App;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ProfileFragment extends MvpAppCompatFragment {

    public static final String PROFILE = "Profile";

    private SwipeRefreshLayout mSwipe;
    private CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView recyclerView;

    @Inject
    DataRepository dataRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView balance = view.findViewById(R.id.balance);
        recyclerView = view.findViewById(R.id.rv_changes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipe = view.findViewById(R.id.swipe_refresh);
        App.getComponent().inject(this);
        Bundle bundle = this.getArguments();
        UserModel userModel = (UserModel) bundle.getSerializable(PROFILE);
        balance.setText(String.format(getString(R.string.balance), userModel.getBalance()));

        checkForOperationUpdates();

        mSwipe.setOnRefreshListener(() -> {
            mSwipe.setRefreshing(true);
            checkForOperationUpdates();

            disposable.add(dataRepository.getActualInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(actualUserModel -> {
                                    balance.setText(String.format(getString(R.string.balance), actualUserModel.getBalance()));

                                },
                            throwable -> Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show()));
        });
        return view;
    }

    public static ProfileFragment newInstance(UserModel userModel){
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ProfileFragment.PROFILE, userModel );
        fragment.setArguments(bundle);
        return fragment;
    }

    private void checkForOperationUpdates(){
        disposable.add(dataRepository.getPayment()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paymentResponse -> {
                            ChangesAdapter changesAdapter = new ChangesAdapter(paymentResponse);
                            recyclerView.setAdapter(changesAdapter);
                            mSwipe.setRefreshing(false);

                        },
                        throwable -> Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show()));
    }
}
