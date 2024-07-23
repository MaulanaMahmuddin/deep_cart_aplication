package com.example.deepcart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.deepcart.databinding.ActivityMainBinding
import com.example.deepcart.databinding.CheckoutLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: CheckoutLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CheckoutLayoutBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.continueShoppingButton.setOnClickListener {
            finish()
        }

    }
}