package com.example.deepcart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.deepcart.databinding.ActivityMainBinding
import com.example.deepcart.retrofit.PostAdapter
import com.example.deepcart.retrofit.PostResponse
import com.example.deepcart.retrofit.RetrofitClient
import kotlinx.coroutines.*
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()
    private lateinit var binding: ActivityMainBinding
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.cartRecyclerView.setHasFixedSize(true)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)

        // Start loading data periodically
        startLoadingDataPeriodically()

        binding.checkoutButton.setOnClickListener {
            val totalAmount = binding.totalAmountTextView.text.toString()

            val intent = Intent(this, CheckoutActivity::class.java).apply {
                putExtra("TOTAL_AMOUNT", totalAmount)
            }
            startActivity(intent)
        }
    }

    private fun startLoadingDataPeriodically() {
        coroutineScope.launch {
            while (isActive) {
                loadData()
                delay(3000) // Delay for 5 seconds
            }
        }
    }

    private suspend fun loadData() {
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.apiService.getViewKeranjang().execute()
            }
            if (response.isSuccessful) {
                response.body()?.let {

                    list.clear()
                    val filteredList = it.filter { item -> item.idKeranjang == 1 }
                    list.addAll(filteredList)

                    // Logging filtered data for debugging
                    Log.d("MainActivity", "Filtered Data: $filteredList")

                    Log.d("MainActivity", "Data received: ${filteredList.size} items")

                    // Create the adapter with the filtered list
                    val adapter = PostAdapter(list)

                    // Calculate the total amount for the filtered items
                    var totalAmount = 0.0

                    filteredList.forEach { item ->
                        val model = PostResponse(hrgBarang = item.hrgBarang)
                        totalAmount += item.qtyVKeranjang?.let { qty -> model.calculateTotal(qtyBarang = qty) } ?: 0.0
                    }

                    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
                    formatRupiah.maximumFractionDigits = 0
                    formatRupiah.minimumFractionDigits = 0
                    val totalAmountInRupiah = formatRupiah.format(totalAmount)
                    binding.totalAmountTextView.text = totalAmountInRupiah

                    binding.cartRecyclerView.adapter = adapter
                    binding.log.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
                } ?: run {
                    binding.log.visibility = View.VISIBLE
                }
            } else {
                Log.d("MainActivity", "Response not successful: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "API call failed", e)
            binding.log.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
