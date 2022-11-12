package com.cse.oms.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final List<ProductListInfo> list;
    private Context context;

    public ProductListAdapter(List<ProductListInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_customlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListInfo data = list.get(position);

        holder.ProductId.setText("ProductId: "+Integer.toString(data.getProductid()));
        holder.ProductCode.setText("ProductCode: "+data.getProductcode());
        holder.Name.setText("Name: "+data.getName());
        holder.Description.setText("Description: "+data.getDescription());
        holder.TradePrice.setText("TradePrice: "+Float.toString(data.getTradeprice()));
        holder.ProductStrength.setText("ProductStrength: "+Integer.toString(data.getProductstrength()));
        holder.PackSize.setText("PackSize: "+Float.toString(data.getPacksize()));
        holder.ProductFamilyId.setText("ProductFamilyId: "+Integer.toString(data.getProductfamilyid()));
        holder.ProductFamilyName.setText("ProductFamilyName: "+data.getProductfamilyname());
        holder.ProductCategory.setText("ProductCategory: "+data.getProductcategory());
        holder.COGS.setText("COGS: "+data.getCogs());
        holder.MRP.setText("MRP: "+Float.toString(data.getMrp()));
        holder.Vat.setText("Vat: "+Float.toString(data.getVat()));
        holder.VatUnit.setText("VatUnit: "+data.getVatunit());
        holder.Discounted.setText("Discounted: "+Float.toString(data.getDiscounted()));
        holder.TPUnit.setText("TPUnit: "+Integer.toString(data.getTpunit()));
        holder.ColdChangeProduct.setText("ColdChangeProduct: "+data.getColdchangeproduct()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView ProductId, ProductCode, Name,Description,TradePrice,ProductStrength,PackSize,
                ProductFamilyId,ProductFamilyName,ProductCategory,COGS,MRP,Vat,VatUnit,Discounted,TPUnit,ColdChangeProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductId = (TextView)itemView.findViewById(R.id.tv_ProductId);
            ProductCode = (TextView)itemView.findViewById(R.id.tv_ProductCode);
            Name = (TextView)itemView.findViewById(R.id.tv_Name);
            Description = (TextView)itemView.findViewById(R.id.tv_Description);
            TradePrice = (TextView)itemView.findViewById(R.id.tv_TradePrice);
            ProductStrength = (TextView)itemView.findViewById(R.id.tv_ProductStrength);
            PackSize = (TextView)itemView.findViewById(R.id.tv_PackSize);
            ProductFamilyId = (TextView)itemView.findViewById(R.id.tv_ProductFamilyId);
            ProductFamilyName = (TextView)itemView.findViewById(R.id.tv_ProductFamilyName);
            ProductCategory = (TextView)itemView.findViewById(R.id.tv_ProductCategory);
            COGS = (TextView)itemView.findViewById(R.id.tv_COGS);
            MRP =(TextView)itemView.findViewById(R.id.tv_MRP);
            Vat = (TextView)itemView.findViewById(R.id.tv_Vat);
            VatUnit = (TextView)itemView.findViewById(R.id.tv_VatUnit);
            Discounted = (TextView)itemView.findViewById(R.id.tv_Discounted);
            TPUnit =(TextView)itemView.findViewById(R.id.tv_TPUnit);
            ColdChangeProduct =(TextView)itemView.findViewById(R.id.tv_ColdChangeProduct);
        }
    }
}
