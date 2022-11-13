package com.cse.oms.CustomerListRoomDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CustomerListDAO {

    @Insert
    public void insertCustomerList(CustomerListInfo customerListInfo);


    @Query("SELECT * FROM CustomerListInfo")
    public List<CustomerListInfo> getAllCustomer();

//    @Query("SELECT * FROM LoginResInfo WHERE empId = :myposition")
//    public List<CustomerListInfo> getAllDatafromRow(int myposition);

//    @Query("SELECT * FROM StudentInfo")
//    public List<LoginResInfo> getAllStudent();
//
//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
