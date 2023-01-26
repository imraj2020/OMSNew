package com.cse.oms.MyAdapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.Network.OrderStatus;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;
import com.cse.oms.ui.CustomerList.CustomerListFragment;
import com.cse.oms.ui.createorder.model.OrderProductsModel;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.ViewHolder> {

    private final List<OrderStatus> list;
    private Context context;

    public OrderStatusAdapter(List<OrderStatus> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orderstatuscustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatus data = list.get(position);

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }





        holder.OrderNo.setText(data.getOrderNo());
        holder.CustomerId.setText(data.getCustomerId());
        holder.OrderDate.setText(data.getOrderDate());
        holder.DeliveryDate.setText(data.getDeliveryDate());
        holder.OrderStatus.setText(data.getOrderStatus()+"\n");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView OrderNo, CustomerId, OrderDate,DeliveryDate,Note,TerritoryId,SCId,OrderStatus;
        CardView MyCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            OrderNo = (TextView)itemView.findViewById(R.id.TvOrderNo);
            CustomerId = (TextView)itemView.findViewById(R.id.TvCustomerId);
            OrderDate = (TextView)itemView.findViewById(R.id.TvOrderDate);
            DeliveryDate = (TextView)itemView.findViewById(R.id.TvDeliveryDate);
            OrderStatus = (TextView)itemView.findViewById(R.id.TvOrderStatus);
            MyCardView = itemView.findViewById(R.id.orderstatuscard);

        }
    }
}

