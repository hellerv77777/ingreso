package co.com.ceiba.mobile.pruebadeingreso.app.services;

import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.models.PostBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiData {

    @GET(Endpoints.GET_USERS)
    Call<List<UserEntity>> downLoadUser();

    @GET(Endpoints.GET_POST_USER)
    Call<List<PostBean>> getPostUser(@Query("userId") String userId);
}
