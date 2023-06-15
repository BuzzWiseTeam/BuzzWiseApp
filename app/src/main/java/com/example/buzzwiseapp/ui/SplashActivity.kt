package com.example.buzzwiseapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.data.ViewModelFactory
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.databinding.ActivitySplashBinding
import com.example.buzzwiseapp.ui.auth.LoginActivity
import com.example.buzzwiseapp.ui.home.dataStore
import com.example.buzzwiseapp.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*auth = Firebase.auth
        val firebaseUser = auth.currentUser

        if (firebaseUser != null) {
            // Not signed in, launch the Login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }*/

        binding.elevatedButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val welcomeViewModel by viewModels<SplashViewModel> {
            ViewModelFactory(
                UserPreference.getInstance(dataStore)
            )
        }
        var isLogin = false

        welcomeViewModel.getUser().observe(this) { model ->
            isLogin = if(model.isLogin) {
                UserPreference.setToken(model.token)
                true
            } else {
                false
            }
        }
        activityScope.launch {
            delay(2000L)
            runOnUiThread {
                if(isLogin) {
                    MainActivity.start(this@SplashActivity)
                } else {
                    LoginActivity.start(this@SplashActivity)
                }
                finish()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activityScope.coroutineContext.cancelChildren()
    }
}