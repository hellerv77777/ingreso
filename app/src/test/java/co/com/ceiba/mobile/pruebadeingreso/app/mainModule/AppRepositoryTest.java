package co.com.ceiba.mobile.pruebadeingreso.app.mainModule;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;
import co.com.ceiba.mobile.pruebadeingreso.app.mainModule.models.PostBean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppRepositoryTest {

    @Mock
    AppRepository appRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(AppRepository.class);
    }

    @Test
    public void getUsers() {

        when(appRepository.getUsers("")).thenReturn(new LiveData<List<UserEntity>>() {
            @Override
            public void observe( LifecycleOwner owner,  Observer<? super List<UserEntity>> observer) {
                super.observe(owner, observer);

                System.out.println("data: "+observer);
            }
        });
    }

    @Test
    public void downLoadUser() {

        when(appRepository.downLoadUser()).thenReturn(new MutableLiveData<String>(){});

    }

    @Test
    public void getPost( ) {
        when(appRepository.getPost("1")).thenReturn(new MutableLiveData<List<PostBean>>(){});
    }
}