package com.cse.oms.MyAdapters;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;
import com.cse.oms.ui.CustomerList.CustomerListFragment;
import com.cse.oms.ui.createorder.model.OrderProductsModel;

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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerListInfo data = userModelList.get(position);




        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }




        setBoldText(holder.Name, "Name: ", data.getName());
        holder.CustomerId.setText("(" + data.getCustomerid()+")");
        setBoldText(holder.DepotName, "Depot: ", data.getDepotname()+" ");
        setBoldText(holder.Address, "Address: ", data.getAddress());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClickListener.selectedUser(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    private void setBoldText(TextView textView, String boldText, String regularText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString boldString = new SpannableString(boldText);
        boldString.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(boldString);
        builder.append(regularText);
        textView.setText(builder);
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
        CardView MyCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            Name = (TextView) itemView.findViewById(R.id.tvs_Name);
            CustomerId = (TextView) itemView.findViewById(R.id.tvs_CustomerId);
            DepotName = (TextView) itemView.findViewById(R.id.tvs_DepotName);
            Address = (TextView) itemView.findViewById(R.id.tvs_Address);
            MyCardView = itemView.findViewById(R.id.mycardview);
            //rootLayout = itemView.findViewById(R.id.rootLayout1);

        }
    }
}
