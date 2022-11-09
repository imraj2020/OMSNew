package com.cse.oms.LoginResRoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = LoginResInfo.class, version = 1, exportSchema = false)
public abstract class LoginResRoomDB extends RoomDatabase {

    public abstract LoginResDAO loginResDAO();

    public static LoginResRoomDB INSTANCE;

    public  static LoginResRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(),LoginResRoomDB.class,"UserInfo.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
