package com.cse.oms.CreateOrderRoomDatabase.interfaces;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cse.oms.CreateOrderRoomDatabase.models.DraftOrderModel;
import com.cse.oms.CreateOrderRoomDatabase.models.DraftProductModel;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertOrder(DraftOrderModel draftOrderModel);

    @Insert
    void insertMultipleOrder(List<DraftOrderModel> draftOrderModels);

    @Query("SELECT * FROM DraftOrderModel WHERE id = :orderId")
    DraftOrderModel fetchOrderById(int orderId);

    @Query("SELECT * FROM DraftOrderModel WHERE orderStatus=:status")
    List<DraftOrderModel> getAllOrders(int status);

    @Query("SELECT MAX(ID) FROM DraftOrderModel")
    long getLastInsertedID();

    @Update
    void updateOrder(DraftOrderModel movies);

    @Delete
    void deleteOrder(DraftOrderModel movies);


    // Draft Products

    @Insert
    void insertProduct(DraftProductModel draftProductModel);

    @Insert
    void insertMultipleProducts(List<DraftProductModel> draftOrderModels);

    @Query("SELECT * FROM DraftProductModel WHERE id = :productID")
    DraftProductModel fetchDraftProductById(int productID);

    @Query("SELECT * FROM DraftProductModel")
    List<DraftProductModel> getAllDraftProducts();

    @Query("SELECT * FROM DraftProductModel WHERE orderId =:orderId")
    List<DraftProductModel> getProductsByOrder(int orderId);

    @Update
    void updateDraftProduct(DraftProductModel movies);

    @Delete
    void deleteDraftProduct(DraftProductModel movies);
}