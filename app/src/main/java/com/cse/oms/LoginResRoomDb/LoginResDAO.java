package com.cse.oms.LoginResRoomDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginResDAO {

    @Insert
    public void insertLoginResponse(LoginResInfo loginResInfo);

//    @Query("SELECT * FROM StudentInfo")
//    public List<LoginResInfo> getAllStudent();
//
//    @Query("UPDATE StudentInfo SET name = :name, subject = :subject WHERE id = :id")
//    public void updateStudentInfo(String name, String subject, int id);
//
//    @Query("DELETE FROM StudentInfo WHERE id = :id")
//    public void deleteStudentInfo(int id);

}
