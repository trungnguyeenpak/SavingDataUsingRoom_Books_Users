package com.trungnguyeen.demosavingdatausingroom.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by trungnguyeen on 12/10/17.
 */

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User user);

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);

    @Query("SELECT * FROM User")
    public User[] loadAllUsers();

    @Query("SELECT * FROM User WHERE id =:id")
    public User findUserById(int id);

    @Query("SELECT * FROM User WHERE firstName LIKE :name OR lastName LIKE :name")
    public List<User> findUserByName(String name);

    @Query("DELETE FROM User")
    public void deleteAllUser();

}
