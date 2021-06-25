package co.com.ceiba.mobile.pruebadeingreso.app.dataBase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<UserEntity>> getUsers();

    @Query("SELECT * FROM user WHERE name LIKE '%'||:str||'%'")
    LiveData<List<UserEntity>> getUsers(String str);

    @Insert
    void insert(List<UserEntity> body);
}
