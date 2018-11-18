package com.example.nikita.rosbank_tech.UI.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nikita.rosbank_tech.Models.CardOwner;
import com.example.nikita.rosbank_tech.Models.ItemOrder;
import com.example.nikita.rosbank_tech.Models.PaymentRequest;
import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.UI.App;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class FragmentPayment extends Fragment {

    @Inject
    DataRepository dataRepository;

    private CompositeDisposable disposable = new CompositeDisposable();

    private static final String PAYMENT = "Payment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        Bundle bundle = this.getArguments();
        App.getComponent().inject(this);
        EditText cardNumber = view.findViewById(R.id.cardNumber);
        EditText nameEdit = view.findViewById(R.id.nameEdit);
        ItemOrder itemOrder = (ItemOrder) bundle.getSerializable(PAYMENT);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setProduct(itemOrder);


        disposable.add(dataRepository.getActualInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userModel -> {
                            paymentRequest.setLogin(userModel.getLogin());
                            cardNumber.setText(Long.toString(userModel.getCardNumber()));
                            nameEdit.setText(userModel.getName());
                        }
                        ));

        EditText date = view.findViewById(R.id.date);
        EditText cvcEdit = view.findViewById(R.id.cvcEdit);
        EditText countryEdit = view.findViewById(R.id.countryEdit);
        EditText phone = view.findViewById(R.id.phone);

        Button button = view.findViewById(R.id.buyButton);

        button.setOnClickListener(view1 -> {
            CardOwner cardOwner = new CardOwner();
            cardOwner.setCvc(Integer.valueOf(cvcEdit.getText().toString()));
            cardOwner.setCardNumber(Long.valueOf(cardNumber.getText().toString()));
            cardOwner.setMmyy(date.getText().toString());
            cardOwner.setOwnerName(nameEdit.getText().toString());
            cardOwner.setPhoneNumber(phone.getText().toString());
            paymentRequest.setCardInfo(cardOwner);
            disposable.add(dataRepository.buyProduct(paymentRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_SHORT).show(),
                            throwable -> Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show()));
        });

        return view;
    }

    public static FragmentPayment newInstance(ItemOrder itemOrder){
        FragmentPayment fragmentPayment = new FragmentPayment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PAYMENT, itemOrder);
        fragmentPayment.setArguments(bundle);
        return fragmentPayment;
    }
}
