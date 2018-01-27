package com.trungnguyeen.demosavingdatausingroom.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by trungnguyeen on 12/10/17.
 */

@Entity
public class User {

    @PrimaryKey
    public int id;

    public String firstName;
    public String lastName;
    public String avataUrl;
}
