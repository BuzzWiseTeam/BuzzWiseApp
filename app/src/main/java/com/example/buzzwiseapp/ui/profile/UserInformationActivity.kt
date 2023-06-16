package com.example.buzzwiseapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.databinding.ActivityUserInformationBinding

class UserInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}