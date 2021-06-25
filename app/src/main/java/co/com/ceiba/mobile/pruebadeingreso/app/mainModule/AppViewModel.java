package co.com.ceiba.mobile.pruebadeingreso.app.mainModule;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.models.PostBean;

public class AppViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    public AppViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<List<UserEntity>> getUsers(String str) {
       return mRepository.getUsers(str);
    }

    public MutableLiveData<String> downLoadUser() {
        return mRepository.downLoadUser();
    }

    public MutableLiveData<List<PostBean>> getPost(String idUser) {
        return mRepository.getPost(idUser);
    }
}
