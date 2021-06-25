package co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import co.com.ceiba.mobile.pruebadeingreso.app.Utils;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.AppViewModel;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.adapters.MyUserRecyclerViewAdapter;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements UserListener {

    private ActivityMainBinding mBinding;
    private MyUserRecyclerViewAdapter mAdapter;
    private AppViewModel mViewModel;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this).get(AppViewModel.class);


        mAdapter = new MyUserRecyclerViewAdapter(this);
        mBinding.recyclerViewSearchResults.setHasFixedSize(true);
        LinearLayoutManager mLinear = new LinearLayoutManager(this);
        mBinding.recyclerViewSearchResults.setLayoutManager(mLinear);
        mBinding.recyclerViewSearchResults.setAdapter(mAdapter);

        mBinding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                lanzarViewModel(s.toString(),true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lanzarViewModel("",false);
    }

    private void lanzarViewModel(String string, boolean filter) {

        mViewModel.getUsers(string).observe(this, userEntities -> {

            if(userEntities.size()==0){

                mBinding.textMensaje.setVisibility(View.VISIBLE);

                if(!filter){

                    dialog = new LoadingDialog(this);
                    mViewModel.downLoadUser().observe(this, s -> {
                        dialog.dismiss();

                        if(!s.equals("OK")){
                            Toast.makeText(this, Utils.ERROR_MSG, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }

            }else {
                mBinding.textMensaje.setVisibility(View.GONE);
            }
            mAdapter.updateItems(userEntities);
        });
    }

    @Override
    public void onClickUser(UserEntity userEntity) {

        Intent intent = new Intent(this,PostActivity.class);
        intent.putExtra("idUser",userEntity.getId());
        intent.putExtra("name",userEntity.getName());
        intent.putExtra("phone",userEntity.getPhone());
        intent.putExtra("email",userEntity.getEmail());
        startActivity(intent);
    }
}