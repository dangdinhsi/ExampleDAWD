package com.myandroid.dangdinhsi.Entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDAO {
    @Insert(onConflict = REPLACE)
    void insertUser(User user);
    @Query("SELECT * FROM user")
    List<User> getAllUser();

}
