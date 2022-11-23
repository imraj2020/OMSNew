package com.cse.oms.ui.Drafts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.oms.R;

public class DraftsFragment extends Fragment {

    private DraftsViewModel mViewModel;

    public static DraftsFragment newInstance() {
        return new DraftsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drafts_fragment, container, false);



        //
//        @Override
//        public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//            MenuInflater inflater=getMenuInflater();
//            inflater.inflate(R.menu.menu,menu);
//            MenuItem menuItem=menu.findItem(R.id.action_search);
//            menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//                @Override
//                public boolean onMenuItemActionExpand(MenuItem item) {
//                    return true;
//                }
//
//                @Override
//                public boolean onMenuItemActionCollapse(MenuItem item) {
//                    searchlistview.setVisibility(View.GONE);
//                    email.setVisibility(View.VISIBLE);
//                    amount.setVisibility(View.VISIBLE);
//                    buttonl.setVisibility(View.VISIBLE);
//                    monthreset.setVisibility(View.VISIBLE);
//                    adminback.setVisibility(View.VISIBLE);
//                    helper.setVisibility(View.VISIBLE);
//                    return true;
//                }
//            });
//            SearchView searchView=(SearchView) menuItem.getActionView();
//            searchView.setQueryHint("Search");
//
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String newtext) {
//
//                    return false;
//                }
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    searchlistview.setVisibility(View.VISIBLE);
//                    email.setVisibility(View.GONE);
//                    amount.setVisibility(View.GONE);
//                    buttonl.setVisibility(View.GONE);
//                    monthreset.setVisibility(View.GONE);
//                    adminback.setVisibility(View.GONE);
//                    helper.setVisibility(View.GONE);
//                    filter(newText);
//                    return true;
//                }
//                private void filter(String newtext){
//                    ArrayList<User> filterresult=new ArrayList<>();
//                    filterresult.clear();
//
//
//                    for(User item:searches){
//
//                        if(item.getNumber().toLowerCase().contains(newtext.toLowerCase())){
//
//                            filterresult.add(item);
//                        }
//
//                    }
//                    searchadapter.filterlist(filterresult);
//                }
//
//
//            });
//            return super.onCreateOptionsMenu(menu);
//        }
        //
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DraftsViewModel.class);
        // TODO: Use the ViewModel
    }

}