package com.trungnguyeen.demosavingdatausingroom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by trungnguyeen on 12/10/17.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class,
                                    parentColumns = "id",
                                    childColumns = "user_id"))

public class Book {

    @PrimaryKey
    public int bookId;

    public String title;

    @ColumnInfo(name = "user_id")
    public int userId;

}
