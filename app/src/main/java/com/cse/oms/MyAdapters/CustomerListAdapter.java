package com.cse.oms.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private final List<CustomerListInfo> list;
    private Context context;

    public CustomerListAdapter(List<CustomerListInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_customlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerListInfo data = list.get(position);

        holder.TerritoryId.setText("TerritoryId: "+Integer.toString(data.getTerritoryid()));
        holder.TerritoryName.setText("TerritoryName: "+data.getTerritoryname());
        holder.SCId.setText("SCId: "+data.getScid());
        holder.DepotName.setText("DepotName: "+data.getDepotname());
        holder.CustomerId.setText("CustomerId: "+data.getCustomerid());
        holder.Name.setText("Name: "+data.getName());
        holder.Address.setText("Address: "+data.getAddress()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView TerritoryId, TerritoryName, SCId,DepotName,CustomerId,Name,Address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TerritoryId = (TextView)itemView.findViewById(R.id.tvs_TerritoryId);
            TerritoryName = (TextView)itemView.findViewById(R.id.tvs_TerritoryName);
            SCId = (TextView)itemView.findViewById(R.id.tvs_SCId);
            DepotName = (TextView)itemView.findViewById(R.id.tvs_DepotName);
            CustomerId = (TextView)itemView.findViewById(R.id.tvs_CustomerId);
            Name = (TextView)itemView.findViewById(R.id.tvs_Name);
            Address = (TextView)itemView.findViewById(R.id.tvs_Address);

        }
    }
}
