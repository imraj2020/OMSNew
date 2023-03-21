package com.cse.oms.ui.NoOrder

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cse.oms.Network.ApiClient
import com.cse.oms.Network.CustomerResponse
import com.cse.oms.Network.NoOrderApiResponse
import com.cse.oms.R
import com.cse.oms.databinding.NoOrderFragmentBinding
import com.cse.oms.ui.createorder.Utils.Constants
import com.cse.oms.ui.createorder.Utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NoOrderFragment : Fragment() {

    companion object {
        fun newInstance() = NoOrderFragment()
    }

    private lateinit var viewModel: NoOrderViewModel
    private lateinit var binding: NoOrderFragmentBinding
    var CustomerID: String? = null
    private val customers = ArrayList<CustomerResponse>()
    var calendar = Calendar.getInstance()
    private lateinit var OrderDate: EditText
    private lateinit var EntryDate: EditText
    private lateinit var Note: EditText
    private lateinit var Submit: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = NoOrderFragmentBinding.inflate(inflater);

        val intent = requireActivity().intent
        val empId = intent?.getIntExtra("EmpId", 0) ?: 0
        val territoryId = intent?.getIntExtra("TerritoryId", 0) ?: 0 // 0 is the default value if "TerritoryId" is not found
        val salesLineId = intent?.getIntExtra("SalesLineId", 0)?.toString()



      //  Toast.makeText(context, "empId :"+empId+" territoryId: "+territoryId+" salesLineId: "+salesLineId, Toast.LENGTH_SHORT).show()

        OrderDate = binding.ETOrderDate
        EntryDate = binding.ETEntryDate
        Note = binding.ETNote
        Submit = binding.BtnNext
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

        OrderDate.setText(formatter.format(calendar.time))




        Submit.setOnClickListener {
            submitorder()
        }


        OrderDate.setOnClickListener {
            // Get the current date as a Calendar object
            val calendar = Calendar.getInstance()

            // Create a DatePickerDialog with the current date as the default date
            val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->
                        // Format the selected date as "YYYY-MM-DD"
                        val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

                        // Set the selected date in the EditText
                        OrderDate.setText(selectedDate)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }


        EntryDate.setOnClickListener {
            // Get the current date as a Calendar object
            val calendar = Calendar.getInstance()

            // Create a DatePickerDialog with the current date as the default date
            val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->
                        // Format the selected date as "YYYY-MM-DD"
                        val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

                        // Set the selected date in the EditText
                        EntryDate.setText(selectedDate)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }




        Customerlist()

        return binding.root
    }


    fun Customerlist() {

        val intent = activity?.intent
        val tid = intent?.getIntExtra("TerritoryId", 0)
        val Scid = intent?.getIntExtra("SalesLineId", 0)

        val call = tid?.let {
            if (Scid != null) {
                ApiClient.getUserService().getAllCustomer(it, Scid)
            } else {
                null
            }
        }

        call?.enqueue(object : retrofit2.Callback<List<CustomerResponse>> {

            override fun onResponse(call: retrofit2.Call<List<CustomerResponse>>, response: Response<List<CustomerResponse>>) {

                if (response.isSuccessful()) {

                    val nlist = response.body()
                    // Toast.makeText(context, "Response Successful", Toast.LENGTH_SHORT).show()
                    response.body()?.let { customers.addAll(it) }
                    if (nlist != null) {
                        addSpinnerData(nlist)
                    }

                } else {
                    Toast.makeText(context, "Retrive Failed", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: retrofit2.Call<List<CustomerResponse>>, t: Throwable) {
                // Holidayres.setText(t.getMessage());
                Toast.makeText(context, "Retrive Failed", Toast.LENGTH_SHORT).show()
            }
        })


    }


    fun addSpinnerData(body: List<CustomerResponse>) {
        binding.tvCustomerLists.setOnClickListener {
            // Initialize dialog
            val dialog = Dialog(requireContext())

            // set custom dialog
            dialog.setContentView(R.layout.dialog_searchable_spinner)

            // set transparent background
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // show dialog
            dialog.show()

            // Initialize and assign variable
            val editText = dialog.findViewById<EditText>(R.id.edit_text)
            val listView = dialog.findViewById<ListView>(R.id.list_view)

            // Initialize array adapter
            val customerResponseList = ArrayList<String>()
            customerResponseList.add(0, "select")
            for (i in 1 until body.size) {
                customerResponseList.add(i, "${body[i].name}\n (${body[i].customerId})\nAddress:${body[i].address}\n\n")
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, customerResponseList)
            listView.adapter = adapter
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable?) {}
            })


            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                binding.tvCustomerLists.text = adapter.getItem(position)

                try {
                    CustomerID = getCustomerIdFromName(binding.tvCustomerLists.text.toString())
                } catch (e: Exception) {
                    Utilities.showLogcatMessage("binding.tvCustomerList $e")
                }

                // Dismiss dialog
                dialog.dismiss()
            }
        }
    }

    fun getCustomerIdFromName(name: String): String {
        for (customerModel in customers) {
            if ((customerModel.name + "\n (" + customerModel.customerId + ")\nAddress:" + customerModel.address + "\n\n").equals(name, ignoreCase = true))
                return customerModel.customerId
        }
        return Constants.DUMMY_USER
    }

    fun submitorder() {


        val intent = requireActivity().intent
        val empId = intent?.getIntExtra("EmpId", 0) ?: 0
        val territoryId = intent?.getIntExtra("TerritoryId", 0) ?: 0 // 0 is the default value if "TerritoryId" is not found
        val salesLineId = intent?.getIntExtra("SalesLineId", 0)?.toString() // 0 is the default value if "SalesLineId" is not found




        val myrequest = NoOrderApiResponse(CustomerID, OrderDate.text.toString(), EntryDate.text.toString(), 1, empId, Note.text.toString(), territoryId, salesLineId)



        val orderStatusResponse: Call<NoOrderApiResponse> = ApiClient.getUserService().createNoOrder(myrequest)
        orderStatusResponse.enqueue(object : Callback<NoOrderApiResponse> {
            override fun onResponse(call: Call<NoOrderApiResponse>, response: Response<NoOrderApiResponse>) {
                if (response.isSuccessful) {

                    val responses = response.body()


                    if (responses != null) {
                        Toast.makeText(requireContext(), "Status: "+responses.message+"\n"+"Order No:" + responses.orderNo, Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(requireContext(), "Sorry something went wrong", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NoOrderApiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "No Data Found !!! ", Toast.LENGTH_LONG).show()
            }

        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}