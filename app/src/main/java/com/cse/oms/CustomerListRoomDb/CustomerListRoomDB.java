package com.cse.oms.CustomerListRoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cse.oms.LoginResRoomDb.LoginResDAO;
import com.cse.oms.LoginResRoomDb.LoginResInfo;

@Database(entities = CustomerListInfo.class, version = 1, exportSchema = false)
public abstract class CustomerListRoomDB extends RoomDatabase {

    public abstract CustomerListDAO customerListDAO();

    public static CustomerListRoomDB INSTANCE;

    public  static CustomerListRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(), CustomerListRoomDB.class,"CustomerList.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
