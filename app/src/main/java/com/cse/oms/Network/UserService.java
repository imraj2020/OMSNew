package com.cse.oms.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {


    @POST("api/HomeApi/Registration")
    Call<Register> REGISTER_CALL(@Body Register register);



    @POST("api/HomeApi/Login")
    Call<LoginResponse> userLogin(
            @Query("networkId")  String networkId,
            @Query("password") String password
    );
}
