package co.com.ceiba.mobile.pruebadeingreso.app.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.app.dataBase.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile AppDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "app_db").build();
                }
            }
        }
        return INSTANCE;
    }
}
