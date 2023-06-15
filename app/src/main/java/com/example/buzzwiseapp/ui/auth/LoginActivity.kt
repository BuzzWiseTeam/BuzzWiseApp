package com.example.buzzwiseapp.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.buzzwiseapp.R
import com.example.buzzwiseapp.data.LoginPayload
import com.example.buzzwiseapp.data.RegisterPayload
import com.example.buzzwiseapp.data.ViewModelFactory
import com.example.buzzwiseapp.data.api.ApiConfig
import com.example.buzzwiseapp.data.model.UserModel
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.data.response.LoginResponse
import com.example.buzzwiseapp.databinding.ActivityLoginBinding
import com.example.buzzwiseapp.ui.SplashActivity
import com.example.buzzwiseapp.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.prefs.Preferences

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvCancel.setOnClickListener{
            startActivity(Intent(this, SplashActivity::class.java))
        }

        /*binding.signInButton.setOnClickListener{
            signIn()
        }*/
        binding.buttonToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.buttonLogin.setOnClickListener{
            userLogin()
        }

        setViewModel()
        setView()

        /*// Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // Initialize Firebase Auth
        auth = Firebase.auth*/
    }

    private fun setViewModel() {
        loginViewModel = ViewModelProvider(
            this, ViewModelFactory(
                UserPreference
                    .getInstance(dataStore)
            )
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this) {
            this.userModel = it
        }
    }

    private fun setView(){
        binding.buttonToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        /*binding.buttonLogin.setOnClickListener{
            //userLogin()
        }*/
    }

    private fun userLogin() {
        val email = binding.edLoginEmail.text.toString()
        val password = binding.edLoginPass.text.toString()

        val payload = LoginPayload(email, password)
        val client = ApiConfig.getApiService().loginUser(payload)

        if(password.length > 7) {
            showLoading(true)
            client.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            showLoading(false)
                            loginViewModel.login()
                            val loginResponse = response.body()
                            val token = loginResponse?.data?.user?.stsTokenManager?.refreshToken
                            loginViewModel.saveUser(UserModel(token!!, true))
                            Log.d("OnSuccessful : ", response.body().toString())
                            finish()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            showLoading(false)
                            Toast.makeText(
                                this@LoginActivity,
                                "Incorrect Password or Email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        showLoading(false)
                        Log.d("OnFailure : ", t.message.toString())
                    }
                })
        } else {
            Toast.makeText(
                this@LoginActivity,
                "Password must be at least 8 characters",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBarMain.visibility= if (isLoading) View.VISIBLE else View.GONE
    }

    /*private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }*/

    companion object {
        private const val TAG = "LoginActivity"
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
        }
    }
}