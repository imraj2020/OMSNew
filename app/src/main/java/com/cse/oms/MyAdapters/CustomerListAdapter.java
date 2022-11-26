package com.cse.oms.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> implements Filterable{

    private List<CustomerListInfo> list;
    public List<CustomerListInfo> userModelList = new ArrayList<>();
    public List<CustomerListInfo> getUserModelListFilter= new ArrayList<>();
    private Context context;
    public UserClickListener userClickListener;


    public interface UserClickListener{
        void selectedUser(CustomerListInfo customerListInfo);
    }

    //List<CustomerListInfo> list
    public CustomerListAdapter(List<CustomerListInfo>userModels, Context context,UserClickListener userClickListener) {
        this.userModelList = userModels;
        this.getUserModelListFilter= userModels;
        this.userClickListener = userClickListener;
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
        CustomerListInfo data = userModelList.get(position);

        holder.TerritoryId.setText("TerritoryId: " + Integer.toString(data.getTerritoryid()));
        holder.TerritoryName.setText("TerritoryName: " + data.getTerritoryname());
        holder.SCId.setText("SCId: " + data.getScid());
        holder.DepotName.setText("DepotName: " + data.getDepotname());
        holder.CustomerId.setText("CustomerId: " + data.getCustomerid());
        holder.Name.setText("Name: " + data.getName());
        holder.Address.setText("Address: " + data.getAddress() + "\n");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListener.selectedUser(data);
            }
        });
    }

    @Override
    public int getItemCount() {

        //list.size
        return userModelList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constrains) {
                FilterResults filterResults = new FilterResults();
                if(constrains==null || constrains.length()==0){
                    filterResults.values = getUserModelListFilter;
                    filterResults.count = getUserModelListFilter.size();
                } else{

                    String searchstr = constrains.toString().toLowerCase();
                    List<CustomerListInfo> userModels= new ArrayList<>();
                    for (CustomerListInfo userModel : getUserModelListFilter){
                        if (userModel.getName().toLowerCase().contains(searchstr)){
                             userModels.add(userModel);
                        }
                    }

                    filterResults.values = userModels;
                    filterResults.count = userModels.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                userModelList = (List<CustomerListInfo>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView TerritoryId, TerritoryName, SCId, DepotName, CustomerId, Name, Address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TerritoryId = (TextView) itemView.findViewById(R.id.tvs_TerritoryId);
            TerritoryName = (TextView) itemView.findViewById(R.id.tvs_TerritoryName);
            SCId = (TextView) itemView.findViewById(R.id.tvs_SCId);
            DepotName = (TextView) itemView.findViewById(R.id.tvs_DepotName);
            CustomerId = (TextView) itemView.findViewById(R.id.tvs_CustomerId);
            Name = (TextView) itemView.findViewById(R.id.tvs_Name);
            Address = (TextView) itemView.findViewById(R.id.tvs_Address);

        }
    }
}
