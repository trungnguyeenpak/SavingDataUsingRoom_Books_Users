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
public interface BookDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBook(Book book);

    @Update
    public void updateBook(Book book);

    @Delete
    public void deleteBook(Book book);

    @Query("SELECT * FROM Book")
    public List<Book> loadAllBooks();

    @Query("SELECT * FROM Book WHERE bookId = :bookId")
    public Book findBookByID(int bookId);

    @Query("SELECT * FROM Book WHERE title LIKE :title")
    public List<Book> findBookByName(String title);

    @Query("DELETE FROM Book")
    public void deleteAllBook();
}
