package com.cse.oms.ProductListRoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ProductListInfo.class, version = 1, exportSchema = false)
public abstract class ProductListRoomDB extends RoomDatabase {

    public abstract ProductListDAO productListDAO();

    public static ProductListRoomDB INSTANCE;

    public  static ProductListRoomDB getDbInstance(Context context){

        if(INSTANCE ==null){

            INSTANCE = Room.databaseBuilder( context.getApplicationContext(), ProductListRoomDB.class,"ProductList.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;


    }
}
