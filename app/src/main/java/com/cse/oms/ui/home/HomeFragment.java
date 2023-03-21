package com.cse.oms.ui.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cse.oms.LoginResRoomDb.LoginResInfo;
import com.cse.oms.LoginResRoomDb.LoginResRoomDB;
import com.cse.oms.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView EmpId,FullName,EmpNetworkId,EmpCode,Email,MobileNo,DepartmentName,
            DesignationName,BUId,BUName,SalesLineId,SalesLineName,RegionId,RegionName,TeamId,TeamName,
            TerritoryId,TerritoryName,Welcomes;
    ImageView OMS;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding =FragmentHomeBinding.inflate(inflater);

        final TextView textView = binding.textHome;

        EmpId = binding.tvEmpId;
        FullName = binding.tvFullName;
        EmpNetworkId = binding.tvEmpNetworkId;
        EmpCode = binding.tvEmpCode;
        Email = binding.tvEmail;
        MobileNo = binding.tvMobileNo;
        DepartmentName = binding.tvDepartmentName;
        DesignationName = binding.tvDesignationName;
        BUId = binding.tvBUId;
        BUName = binding.tvBUName;
        SalesLineId = binding.tvSalesLineId;
        SalesLineName = binding.tvSalesLineName;
        RegionId = binding.tvRegionId;
        RegionName = binding.tvRegionName;
        TeamId = binding.tvTeamId;
        TeamName = binding.tvTeamName;
        TerritoryId = binding.tvTerritoryId;
        TerritoryName = binding.tvTerritoryName;
        OMS = binding.omsnew;
        Welcomes = binding.welcomes;


        //intent

        //test
        Intent intents = getActivity().getIntent();
        int mypositions = intents.getIntExtra("EmpId",0);
        //Room
        LoginResRoomDB database = LoginResRoomDB.getDbInstance(requireContext());

        if(mypositions ==0){

            List<LoginResInfo> list = database.loginResDAO().getAllData();
            int empid = list.get(0).getEmpId();
            String fullname = list.get(0).getFullName();
            String empnetworkid = list.get(0).getEmpNetworkId();
            String empcode = list.get(0).getEmpCode();
            String email = list.get(0).getEmail();
            String mobileon = list.get(0).getMobileNo();
            String departmentname = list.get(0).getDepartmentName();
            String designationname = list.get(0).getDesignationName();
            int buid = list.get(0).getbUId();
            String buname = list.get(0).getbUName();

            int saleslineid = list.get(0).getSalesLineId();
            String saleslinename = list.get(0).getSalesLineName();
            int regionid = list.get(0).getRegionId();
            String regionname = list.get(0).getRegionName();
            int teamid = list.get(0).getTeamId();
            String teamname = list.get(0).getTeamName();
            int territoryid = list.get(0).getTerritoryId();
            String territoryname = list.get(0).getTerritoryName();

            Welcomes.setText("Welcome  " + fullname);
            EmpId.setText("EmpId: " + empid);
            FullName.setText("FullName: " + fullname);
            EmpNetworkId.setText("EmpNetworkId: "+empnetworkid);
            EmpCode.setText("EmpCode: "+empcode);
            Email.setText("Email: "+email);
            MobileNo.setText("MobileNo: "+mobileon);
            DepartmentName.setText("DepartmentName: "+departmentname);
            DesignationName.setText("DesignationName: " + designationname);
            BUId.setText("BUId: " + buid);
            BUName.setText("BUName: " + buname);
            SalesLineId.setText("SalesLineId: " + saleslineid);
            SalesLineName.setText("SalesLineName: " + saleslinename);
            RegionId.setText("RegionId: " + regionid);
            RegionName.setText("RegionName: " + regionname);
            TeamId.setText("TeamId: " + teamid);
            TeamName.setText("TeamName: " + teamname);
            TerritoryId.setText("TerritoryId: " + territoryid);
            TerritoryName.setText("TerritoryName: " + territoryname);

        }

        else {
            List<LoginResInfo> list = database.loginResDAO().getAllDatafromRow(mypositions);
            
            int empid = list.get(0).getEmpId();
            String fullname = list.get(0).getFullName();
            String empnetworkid = list.get(0).getEmpNetworkId();
            String empcode = list.get(0).getEmpCode();
            String email = list.get(0).getEmail();
            String mobileon = list.get(0).getMobileNo();
            String departmentname = list.get(0).getDepartmentName();
            String designationname = list.get(0).getDesignationName();
            int buid = list.get(0).getbUId();
            String buname = list.get(0).getbUName();

            int saleslineid = list.get(0).getSalesLineId();
            String saleslinename = list.get(0).getSalesLineName();
            int regionid = list.get(0).getRegionId();
            String regionname = list.get(0).getRegionName();
            int teamid = list.get(0).getTeamId();
            String teamname = list.get(0).getTeamName();
            int territoryid = list.get(0).getTerritoryId();
            String territoryname = list.get(0).getTerritoryName();


            setBoldText(EmpNetworkId, "Network Id: ", empnetworkid);
            setBoldText(Email, "Email: ", email);
            setBoldText(MobileNo, "Mobile No: ", mobileon);




            Welcomes.setText("Welcome  " + fullname);
//            EmpId.setText("EmpId: " + empid);
//            FullName.setText("FullName: " + fullname);
//            EmpNetworkId.setText("Network Id: "+empnetworkid);
//            EmpCode.setText("EmpCode: "+empcode);
//            Email.setText("Email: "+email);
//            MobileNo.setText("Mobile No: "+mobileon);
//            DepartmentName.setText("DepartmentName: "+departmentname);
//            DesignationName.setText("DesignationName: " + designationname);
//            BUId.setText("BUId: " + buid);
//            BUName.setText("BUName: " + buname);
//            SalesLineId.setText("SalesLineId: " + saleslineid);
//            SalesLineName.setText("SalesLineName: " + saleslinename);
//            RegionId.setText("RegionId: " + regionid);
//            RegionName.setText("RegionName: " + regionname);
//            TeamId.setText("TeamId: " + teamid);
//            TeamName.setText("TeamName: " + teamname);
//            TerritoryId.setText("TerritoryId: " + territoryid);
//            TerritoryName.setText("TerritoryName: " + territoryname);

        }






        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return binding.getRoot();
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}