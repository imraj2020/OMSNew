package com.cse.oms.ProductListRoomDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductListDAO {

    @Insert
    public void insertProduct(ProductListInfo productListInfo);

    @Query("SELECT * FROM ProductListInfo")
    public List<ProductListInfo> getAllProduct();
//    @Query("SELECT * FROM ProductListInfo WHERE empId = :myposition")
//    public List<ProductListInfo> getAllDatafromRow(int myposition);


//
//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
