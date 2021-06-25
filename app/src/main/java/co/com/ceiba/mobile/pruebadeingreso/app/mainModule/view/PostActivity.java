package co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import co.com.ceiba.mobile.pruebadeingreso.app.Utils;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.AppViewModel;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.view.adapters.MyPostRecyclerViewAdapter;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding;

public class PostActivity extends AppCompatActivity {


    private AppViewModel mViewModel;
    private MyPostRecyclerViewAdapter mAdapter;
    private ActivityPostBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        mBinding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mViewModel = new ViewModelProvider(this).get(AppViewModel.class);

        Bundle bundle = getIntent().getExtras();

        mBinding.name.setText(bundle.getString("name"));
        mBinding.phone.setText(bundle.getString("phone"));
        mBinding.email.setText(bundle.getString("email"));

        mAdapter = new MyPostRecyclerViewAdapter();
        mBinding.recyclerViewPostsResults.setHasFixedSize(true);
        LinearLayoutManager mLinear = new LinearLayoutManager(this);
        mBinding.recyclerViewPostsResults.setLayoutManager(mLinear);
        mBinding.recyclerViewPostsResults.setAdapter(mAdapter);

        mViewModel.getPost(bundle.getString("idUser")).observe(this, postBeans -> {

            if(postBeans==null){

                Toast.makeText(this, Utils.ERROR_MSG, Toast.LENGTH_SHORT).show();
                return;
            }
            mAdapter.updateItems(postBeans);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}