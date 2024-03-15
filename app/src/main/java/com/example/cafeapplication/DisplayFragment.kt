package com.example.cafeapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeapplication.databinding.FragmentDisplayBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayFragment : Fragment() {
    private lateinit var binding: FragmentDisplayBinding
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var recyclerView: RecyclerView
    private var foodList: MutableList<Food> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayBinding.inflate(inflater, container, false)
        foodAdapter = FoodAdapter(requireContext(), foodList)
        recyclerView = binding.recyclerView
        recyclerView.adapter = foodAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getFoodData()
        return binding.root
    }

    private fun getFoodData() {
        val service = RetrofitClient.getService()
        val call = service.getFoodData()
        call.enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if (response.isSuccessful) {
                    foodList.clear()
                    // Add all the received food items to the list
                    response.body()?.let { foodList.addAll(it) }
                    // Notify adapter about the data change
                    foodAdapter.notifyDataSetChanged()
                } else {

                }
            }
            override fun onFailure(call: Call<List<Food>>, t: Throwable) {

            }
        })
    }
}
