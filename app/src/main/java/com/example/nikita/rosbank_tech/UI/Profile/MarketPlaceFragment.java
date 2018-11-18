package com.example.nikita.rosbank_tech.UI.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.nikita.rosbank_tech.Adapters.Expandable.ProductsAdapter;
import com.example.nikita.rosbank_tech.Models.CategoryModel;
import com.example.nikita.rosbank_tech.Models.ProductModel;
import com.example.nikita.rosbank_tech.Persistence.DataRepository;
import com.example.nikita.rosbank_tech.Persistence.Entities.UserModel;
import com.example.nikita.rosbank_tech.R;
import com.example.nikita.rosbank_tech.UI.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketPlaceFragment extends Fragment implements ProductsAdapter.UserClicked {

    @Inject
    DataRepository dataRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);

        App.getComponent().inject(this);
        Bundle bundle = this.getArguments();
        UserModel userModel = (UserModel) bundle.getSerializable("Categories");

        RecyclerView recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        dataRepository.findProducts(userModel).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                List<ProductModel> list = response.body();
                Set<String> categoryModels = new HashSet<>();
                for(int i = 0; i < list.size(); i++){
                    categoryModels.add(list.get(i).getTag());
                }
                HashMap<String, List<ProductModel>> hashMap = new HashMap();
                for(ProductModel productModel: list){
                    if(hashMap.containsKey(productModel.getTag())){
                        hashMap.get(productModel.getTag()).add(productModel);
                    }else{
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(productModel);
                        hashMap.put(productModel.getTag(), arrayList);
                    }
                }
                Iterator it = hashMap.entrySet().iterator();
                ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<>();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    CategoryModel categoryModel = new CategoryModel((String) pair.getKey(),(List<ProductModel>) pair.getValue());
                    categoryModelArrayList.add(categoryModel);
                }

                ProductsAdapter adapter = new ProductsAdapter(categoryModelArrayList, MarketPlaceFragment.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static MarketPlaceFragment newInstance(UserModel userModel) {
        Bundle args = new Bundle();
        args.putSerializable("Categories" , userModel);
        MarketPlaceFragment fragment = new MarketPlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void userClicked(ProductModel product) {
        Fragment fragment = FragmentProductDetails.newInstance(product);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
