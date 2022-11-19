package com.cse.oms.LoginResRoomDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginResDAO {

    @Insert
    public void insertLoginResponse(LoginResInfo loginResInfo);


    @Query("SELECT * FROM LoginResInfo WHERE empId = :myposition")
    public List<LoginResInfo> getAllDatafromRow(int myposition);

    @Query("SELECT * FROM LoginResInfo")
    public List<LoginResInfo> getAllData();
//
//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
