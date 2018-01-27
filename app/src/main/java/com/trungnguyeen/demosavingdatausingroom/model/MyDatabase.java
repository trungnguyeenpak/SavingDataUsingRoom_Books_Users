package com.trungnguyeen.demosavingdatausingroom.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by trungnguyeen on 12/10/17.
 */


@Database(entities = {User.class, Book.class}, version = 1)

public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDAO getUserDAO();

    public abstract BookDAO getBookDAO();
}
