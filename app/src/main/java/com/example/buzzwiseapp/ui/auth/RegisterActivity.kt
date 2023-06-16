package com.example.buzzwiseapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.buzzwiseapp.data.RegisterPayload
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.databinding.ActivityRegisterBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.buttonRegister.setOnClickListener{
            userRegister()
        }
    }

    private fun setView(){
        //Binding
        binding.edRegisterPass.doOnTextChanged { text, _, _, _ ->
            if (text!!.length < 8){
                binding.layoutRegisterPass.error = "Password must be at least 8 characters"
            } else {
                binding.layoutRegisterPass.error = null
            }
        }

        binding.buttonToLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun userRegister(){
        val name = binding.edRegisterName.text.toString()
        val email = binding.edRegisterEmail.text.toString()
        val password = binding.edRegisterPass.text.toString()

        val payload = RegisterPayload(name, email, password)
        val client = ApiConfig.getApiService().registerUser(payload)

        if(password.length > 7) {
            showLoading(true)
            client.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            showLoading(false)
                            Log.d("OnSuccessful : ", response.body().toString())
                            Toast.makeText(
                                this@RegisterActivity,
                                "Successfully created an account",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        } else {
                            showLoading(false)
                            Toast.makeText(
                                this@RegisterActivity,
                                "Email is already taken",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        showLoading(false)
                        Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("OnFailure : ", t.message.toString())
                    }
                })
        } else {
            Toast.makeText(this@RegisterActivity, "Password must be at least 8 characters" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarRegister.visibility= if (isLoading) View.VISIBLE else View.GONE
    }

}