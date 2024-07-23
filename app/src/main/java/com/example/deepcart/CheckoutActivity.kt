package com.example.deepcart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deepcart.databinding.CheckoutLayoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: CheckoutLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CheckoutLayoutBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val totalAmount = intent.getStringExtra("TOTAL_AMOUNT")

        binding.totalAmountTextView.text = totalAmount

        binding.continueShoppingButton.setOnClickListener {
            finish()
        }

    }
}