package com.cse.oms.ui.createorder.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cse.oms.CreateOrderRoomDatabase.interfaces.DaoAccess;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftProductModel;

@Database(entities = {DraftOrderModel.class, DraftProductModel.class}, version = 2, exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
