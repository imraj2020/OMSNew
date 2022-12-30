package com.cse.oms.ui.createorder.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.R;
import com.cse.oms.ui.createorder.model.OrderProductsModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductAdapter.SaleProductViewHolder> {
    private final Context context;
    private List<OrderProductsModel> items;

    public SaleProductAdapter(List<OrderProductsModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public SaleProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_product, parent, false);
        return new SaleProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SaleProductViewHolder holder, int position) {
        OrderProductsModel item = items.get(position);
        holder.set(item);
        if (holder.getLayoutPosition() % 2 == 0) {
            holder.rootLayout.setCardBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.rootLayout.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<OrderProductsModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        items = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public class SaleProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductName, tvPackSize;
        EditText etQuantity, etQTY;
        Button btnAdd;
        TextView tvAmount;
        ImageView ivRemove;
        ImageView ivAdd;
        CheckBox cbItem;
        CardView rootLayout;

        public SaleProductViewHolder(View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPackSize = itemView.findViewById(R.id.tvPackSize);
            etQuantity = itemView.findViewById(R.id.etQuantity);
            etQTY = itemView.findViewById(R.id.etQTY);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            ivRemove = itemView.findViewById(R.id.ivRemove);
            ivAdd = itemView.findViewById(R.id.ivAdd);
            cbItem = itemView.findViewById(R.id.cbItem);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            this.setIsRecyclable(false);
        }

        public void set(final OrderProductsModel item) {

            //UI setting code
            tvProductName.setText(item.getName());
            tvPackSize.setText("PackSize: " + item.getPackSize() + "|" + " Price: " + item.getTradePrice());
            // cbItem.setOnCheckedChangeListener(null);
            if (item.getChecked()) {
                cbItem.setChecked(true);
                etQTY.setText(String.valueOf(item.getQuantity()));
            } else {
                cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                          if (isChecked) {
                                                              int adapterPosition = getAdapterPosition();
                                                              if (etQTY.getText().toString().equals("")) {
                                                                  Toast.makeText(context, "Please Enter QTY" + etQTY.getText().toString(), Toast.LENGTH_SHORT).show();
                                                                  cbItem.setChecked(false);
                                                                  items.get(adapterPosition).setChecked(false);
                                                              } else {
                                                                  item.setChecked(true);
                                                                  cbItem.setChecked(true);
                                                                  items.get(adapterPosition).setChecked(true);

                                                                  double quantity = 0.0;
                                                                  try {
                                                                      quantity = Double.parseDouble(etQTY.getText().toString());
                                                                      if (quantity == 0.0)
                                                                          return;
                                                                  } catch (Exception e) {
                                                                      e.printStackTrace();
                                                                  }
                                                                  item.setQuantity(quantity);
                                                                  OrderProductsModel orderProductsModel = new OrderProductsModel();
                                                                  orderProductsModel.setProductId(item.getProductId());
                                                                  orderProductsModel.setName(item.getName());
                                                                  orderProductsModel.setQuantity(quantity);
                                                                  orderProductsModel.setTradePrice(item.getTradePrice());
                                                                  orderProductsModel.setAmount(item.getTradePrice() * quantity);
                                                                  orderProductsModel.setMRP(item.getMRP());
                                                                  EventBus.getDefault().post(orderProductsModel);

                                                                  //EventBus.getDefault().post(new UiModificationEvent(true));
                                                              }
                                                              Toast.makeText(context, "Enter QTY " + etQTY.getText().toString(), Toast.LENGTH_SHORT).show();
                                                          }

                                                      }
                                                  }
                );
            }
        }
    }
}