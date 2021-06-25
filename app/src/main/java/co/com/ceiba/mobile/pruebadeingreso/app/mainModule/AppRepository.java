package co.com.ceiba.mobile.pruebadeingreso.app.mainModule;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.models.PostBean;
import co.com.ceiba.mobile.pruebadeingreso.app.services.MyClientRetrofit;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.AppDataBase;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppRepository {

    private UserDao userDao;

    public AppRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        userDao = db.userDao();
    }

    public LiveData<List<UserEntity>> getUsers(String str) {

        if(str.isEmpty()){ return userDao.getUsers(); }

        return userDao.getUsers(str);
    }

    public MutableLiveData<String> downLoadUser() {

        MutableLiveData<String> resp = new MutableLiveData<>();

        MyClientRetrofit.getInstance().apiData().downLoadUser().enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    insertUsers(response.body());
                    resp.postValue("OK");
                }else {
                    resp.postValue("ERROR");
                }
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable throwable) {
                resp.postValue("ERROR");
            }
        });

        return resp;
    }

    private void insertUsers(List<UserEntity> body) {
        AppDataBase.databaseWriteExecutor.execute(() -> userDao.insert(body));
    }

    public MutableLiveData<List<PostBean>> getPost(String idUser) {

        MutableLiveData<List<PostBean>> post = new MutableLiveData<>();

        MyClientRetrofit.getInstance().apiData().getPostUser(idUser).enqueue(new Callback<List<PostBean>>() {
            @Override
            public void onResponse(Call<List<PostBean>> call, Response<List<PostBean>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    post.postValue(response.body());
                }else {
                    post.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<PostBean>> call, Throwable throwable) {
                post.postValue(null);
            }
        });

        return post;
    }
}
