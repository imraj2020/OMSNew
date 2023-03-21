package com.cse.oms.MyAdapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.oms.CustomerListRoomDb.CustomerListInfo;
import com.cse.oms.ProductListRoomDb.ProductListInfo;
import com.cse.oms.R;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements Filterable {

    private Context context;
    public List<ProductListInfo> userModelList = new ArrayList<>();
    public List<ProductListInfo> getUserModelListFilter= new ArrayList<>();
    public UserClickListener myuserClickListener;

    public interface UserClickListener{
        void selectedUsers(ProductListInfo productListInfo);
    }


    public ProductListAdapter(List<ProductListInfo> userModels, Context context,UserClickListener myuserClickListener) {
        this.userModelList = userModels;
        this.getUserModelListFilter= userModels;
        this.myuserClickListener = myuserClickListener;
        this.context = context;
        notifyDataSetChanged();
    }

    private void setBoldText(TextView textView, String boldText, String regularText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString boldString = new SpannableString(boldText);
        boldString.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(boldString);
        builder.append(regularText);
        textView.setText(builder);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_customlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListInfo data = userModelList.get(position);


        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }


        setBoldText(holder.Name, "Name: ", data.getName());
        holder.ProductCode.setText("(" + data.getProductcode()+")");
        setBoldText(holder.ProductCategory, "ProductCategory: ", data.getProductcategory()+" ");
        setBoldText(holder.TradePrice, "TradePrice: ", Float.toString(data.getTradeprice())+" ");
        setBoldText(holder.PackSize, "PackSize: ", Float.toString(data.getPacksize()));

        //changes
//        holder.Name.setText("Name: "+data.getName());
//        holder.ProductCode.setText("ProductCode: "+data.getProductcode());
//        holder.ProductCategory.setText("ProductCategory: "+data.getProductcategory());
//        holder.TradePrice.setText("TradePrice: "+Float.toString(data.getTradeprice()));
//        holder.PackSize.setText("PackSize: "+Float.toString(data.getPacksize()));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myuserClickListener.selectedUsers(data);
            }
        });
    }

    @Override
    public int getItemCount() {
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
                    List<ProductListInfo> userModels= new ArrayList<>();
                    for (ProductListInfo userModel : getUserModelListFilter){
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
                userModelList = (List<ProductListInfo>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView ProductCode, Name,TradePrice,PackSize,ProductCategory;
        CardView MyCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            Name = (TextView)itemView.findViewById(R.id.tv_Name);
            ProductCode = (TextView)itemView.findViewById(R.id.tv_ProductCode);
            ProductCategory = (TextView)itemView.findViewById(R.id.tv_ProductCategory);
            TradePrice = (TextView)itemView.findViewById(R.id.tv_TradePrice);
            PackSize = (TextView)itemView.findViewById(R.id.tv_PackSize);

            MyCardView = itemView.findViewById(R.id.productcardview);



        }
    }
}
