package com.cse.oms.CustomerListRoomDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CustomerListDAO {

    @Insert
    public void insertCustomerList(CustomerListInfo customerListInfo);


    @Query("SELECT * FROM CustomerListInfo")
    public List<CustomerListInfo> getAllCustomer();

    @Query("SELECT * FROM CustomerListInfo WHERE name = :searchQery")
    public List<CustomerListInfo> getAllDatafromRow(String searchQery);


    @Query("SELECT * FROM CustomerListInfo WHERE name =:searchQuery")
    LiveData<List<CustomerListInfo>> getSearchDatabase(final String searchQuery);

    @Query("DELETE FROM CustomerListInfo")
     void deleteAlls();



//    @Query("SELECT * FROM StudentInfo")
//    public List<LoginResInfo> getAllStudent();
//
//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
