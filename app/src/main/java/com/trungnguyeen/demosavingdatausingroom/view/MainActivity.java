package com.trungnguyeen.demosavingdatausingroom.view;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.trungnguyeen.demosavingdatausingroom.R;
import com.trungnguyeen.demosavingdatausingroom.model.Book;
import com.trungnguyeen.demosavingdatausingroom.model.MyDatabase;
import com.trungnguyeen.demosavingdatausingroom.model.User;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MyDatabase mydb;
    private GenerateDataTask generateDataTask;
    private FetchDataTask fetchDataTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mydb = Room.databaseBuilder(getApplicationContext(),
                       MyDatabase.class, "User_Book").build();

        generateDataTask = new GenerateDataTask(mydb);
        generateDataTask.execute();


        fetchDataTask = new FetchDataTask(mydb, this);
        fetchDataTask.execute();

    }

    public static class GenerateDataTask extends AsyncTask<Void, Void, Void>{

        private MyDatabase mydb;

        public GenerateDataTask(MyDatabase mydb) {
            this.mydb = mydb;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            generateDunmyData();
            return null;
        }


        private void generateDunmyData(){

//        mydb.getBookDAO().deleteAllBook();
//        mydb.getUserDAO().deleteAllUser();

            User user1 = addUser(1, "Tuan", "Nguyeen");
            User user2 = addUser(2, "Toan", "Nguyeen");
            User user3 = addUser(3, "Thong", "Nguyeen");

            addBook(1, "Death-Note", user1);
            addBook(2, "OnPunchMan", user2);
            addBook(3, "Doreamon", user3);
        }


        private Book addBook(final int id, final String title, final User user){

            Book book = new Book();
            book.bookId = id;
            book.title = title;
            book.userId = user.id;
            mydb.getBookDAO().insertBook(book);

            return book;
        }

        private User addUser(final int id, final String firstName, final String lastName){

            User user = new User();
            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            mydb.getUserDAO().insertUser(user);
            return user;
        }
    }

    public static class FetchDataTask extends AsyncTask<Void, Void, List<Book>>{

        private MyDatabase mydb;
        private Activity contextParent;

        public FetchDataTask(MyDatabase mydb, Activity contextParent) {
            this.mydb = mydb;
            this.contextParent = contextParent;
        }


        @Override
        protected List<Book> doInBackground(Void... voids) {
            return fetchData();
        }

        @Override
        protected void onPostExecute(List<Book> bookList) {
            super.onPostExecute(bookList);
            showUI(bookList);
        }


        private List<Book> fetchData(){
            List<Book> books = mydb.getBookDAO().loadAllBooks();
            return books;
        }

        private void showUI(List<Book> bookList){
            if(bookList == null)    return;

            ShowUITask showUITask = new ShowUITask(mydb, bookList, contextParent);
            showUITask.execute(bookList);

        }

        private static class ShowUITask extends AsyncTask<List<Book>, Void, String>{

            private MyDatabase mydb;
            private List<Book> bookList;
            private Activity contextParent;

            public ShowUITask(MyDatabase mydb, List<Book> bookList, Activity contextParent) {
                this.mydb = mydb;
                this.bookList = bookList;
                this.contextParent = contextParent;
            }

            @Override
            protected String doInBackground(List<Book>[] books) {
                StringBuilder sb = new StringBuilder();
                for (Book book: books[0]){
                    User user = mydb.getUserDAO().findUserById(book.userId);
                    sb.append(String.format(Locale.US,
                            "%s, borrowed by %s \n",
                            book.title, user.firstName + " " + user.lastName));
                }
                return sb.toString();
            }

            @Override
            protected void onPostExecute(String sb) {
                super.onPostExecute(sb);
                ((TextView) contextParent.findViewById(R.id.bookList)).setText(sb);
            }
        }

    }
}