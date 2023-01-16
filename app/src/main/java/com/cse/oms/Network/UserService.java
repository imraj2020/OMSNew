package com.cse.oms.Network;

import com.cse.oms.ui.createorder.model.OrderInfo.OrderBaicInfoResponse;
import com.cse.oms.ui.createorder.model.OrderProductsModel;
import com.cse.oms.ui.createorder.model.SubmitOrder;
import com.cse.oms.ui.createorder.model.SubmitOrderResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {


    @POST("api/HomeApi/Registration")
    Call<Register> REGISTER_CALL(@Body Register register);


    @POST("api/HomeApi/Login")
    Call<LoginResponse> userLogin(
            @Query("networkId") String networkId,
            @Query("password") String password
    );

    @GET("api/ProductApi/GetAllProduct")
    Call<List<ProductResponse>> getProduct();


    @GET("api/ProductApi/GetAllProduct")
    Call<List<OrderProductsModel>> getOrderProduct();


    @GET("api/CustomerApi/GetAllCustomer")
    Call<List<CustomerResponse>> getAllCustomer(
            @Query("territoryId") int territoryId,
            @Query("scId") int scId
    );

    @GET("api/NewOrderApi/GetPoInfo")
    Call<OrderBaicInfoResponse> GetPoInfo(
            @Query("orderNo") String orderNo,
            @Query("verId") int verId
    );


    //Submit Order

    @POST("api/NewOrderApi/CreateNewOrder")
    Call<SubmitOrderResponce> SubmitOrder(@Body SubmitOrder submitOrder);



    //Order Status

    @GET("api/OrdersStatusCheckingAPI/GetOrdersStatus")
    Call<List<OrderStatus>> ORDER_STATUS_CALL(@Query("EmpId") String EmpId,
                                              @Query("OrderDate") String OrderDate,
                                              @Query("DeliveryDate") String DeliveryDate,
                                              @Query("CustomerId") String CustomerId

    );

}
