package co.com.ceiba.mobile.pruebadeingreso.app.mainModule;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AppViewModelTest {

    AppViewModel model;

    @Before
    public void setUp() throws Exception {

        Application application = Mockito.mock(Application.class);
        model = new AppViewModel(application);

    }

    @Test
    public void getUsers() {

    }

    @Test
    public void downLoadUser() {
    }

    @Test
    public void getPost() {
    }
}