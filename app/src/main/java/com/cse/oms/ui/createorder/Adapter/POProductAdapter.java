package com.cse.oms.ui.createorder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.R;
import com.cse.oms.ui.createorder.MessageEvent;
import com.cse.oms.ui.createorder.model.OrderInfo.OrderItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class POProductAdapter extends RecyclerView.Adapter<POProductAdapter.AddedProductViewHolder> {
    private final Context context;
    private final List<OrderItem> items;

    public POProductAdapter(List<OrderItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public AddedProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_added_product, parent, false);
        return new AddedProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AddedProductViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.tvProductName.setText(items.get(position).getProductName());
        holder.tvQuantity.setText(String.valueOf(items.get(position).getQuantity()));

        final double quantity = items.get(position).getQuantity();
        double purchasePrice = items.get(position).getTotalPrice();
        //   final double sellPrice = items.get(position).getMRP();
        holder.tvAmount.setText(String.format("%.2f", items.get(position).getTotalPrice()));
        // items.get(position).setAmount(quantity * purchasePrice);
        EventBus.getDefault().post(new MessageEvent(true));


        holder.ivRemove.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class AddedProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName, tvQuantity, tvAmount;
        ImageView ivRemove;

        public AddedProductViewHolder(View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivRemove = itemView.findViewById(R.id.ivRemove);
        }

        public void set(final OrderItem item) {
            //UI setting code

        }
    }
}