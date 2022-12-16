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
import com.cse.oms.ui.createorder.UiModificationEvent;
import com.cse.oms.ui.createorder.model.OrderProductsModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class AddedProductAdapter extends RecyclerView.Adapter<AddedProductAdapter.AddedProductViewHolder> {
    private final Context context;
    private final List<OrderProductsModel> items;

    public AddedProductAdapter(List<OrderProductsModel> items, Context context) {
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


        holder.tvProductName.setText(items.get(position).getName());
        holder.tvQuantity.setText(String.valueOf(items.get(position).getQuantity()));

        final double quantity = items.get(position).getQuantity();
        double purchasePrice = items.get(position).getTradePrice();
        final double sellPrice = items.get(position).getMRP();
        holder.tvAmount.setText(String.format("%.2f", items.get(position).getAmount()));
      //  items.get(position).setAmount(quantity * purchasePrice);
        items.get(position).setProductId(items.get(position).getProductId());
        items.get(position).setQuantity(items.get(position).getQuantity());
        items.get(position).setUnitPrice(items.get(position).getTradePrice());
        items.get(position).setStatus(0);
        EventBus.getDefault().post(new MessageEvent(true));


        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(items.get(position));
                items.remove(items.get(position));
                EventBus.getDefault().post(new MessageEvent(true));

                if (items.size() == 0)
                    EventBus.getDefault().post(new UiModificationEvent(false));
            }
        });
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

        public void set(final OrderProductsModel item) {
            //UI setting code

        }
    }
}