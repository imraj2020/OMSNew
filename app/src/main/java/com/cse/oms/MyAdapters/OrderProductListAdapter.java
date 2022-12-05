package com.cse.oms.MyAdapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;

import java.util.List;

public class OrderProductListAdapter extends RecyclerView.Adapter<OrderProductListAdapter.ViewHolder> {

    private final List<ProductListInfo> list;
    private final Context context;

    public OrderProductListAdapter(List<ProductListInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_list_customlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListInfo data = list.get(position);


        holder.Name.setText("Name: " + data.getName());

        holder.PackSize.setText("PackSize: " + data.getPacksize() + "|" + " Price: " + data.getTradeprice());

        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @RequiresApi(api = Build.VERSION_CODES.P)
                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                         if (isChecked) {
                                                             if (holder.QTY.getText().toString().equals("")) {
                                                                 Toast.makeText(context, "Please Enter QTY" + holder.QTY.getText().toString(), Toast.LENGTH_SHORT).show();
                                                                 holder.cbItem.setChecked(false);
                                                             } else {
                                                                 Toast.makeText(context, "Enter QTY" + holder.QTY.getText().toString(), Toast.LENGTH_SHORT).show();
                                                             }
                                                         }
                                                         //Toast.makeText(context, "Plz Enter QTY", Toast.LENGTH_SHORT).show();

                                                     }
                                                 }
        );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, PackSize;
        EditText QTY;
        CheckBox cbItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tv_ProductName);
            PackSize = itemView.findViewById(R.id.tv_PackSize);
            QTY = itemView.findViewById(R.id.etQTY);
            cbItem = itemView.findViewById(R.id.cbItem);

        }
    }
}
