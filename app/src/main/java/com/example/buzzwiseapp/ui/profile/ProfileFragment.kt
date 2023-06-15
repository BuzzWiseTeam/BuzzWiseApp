package com.example.buzzwiseapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.buzzwiseapp.data.ViewModelFactory
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.databinding.FragmentProfileBinding
import com.example.buzzwiseapp.ui.auth.LoginActivity
import com.example.buzzwiseapp.ui.home.dataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val mainViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.btnEdit.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.btnInformation.setOnClickListener {
            val intent = Intent(activity, UserInformationActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
        return binding.root
    }

    private fun logout() {
        mainViewModel.logout()
        LoginActivity.start(requireActivity())
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireActivity()).apply {
            setMessage("Are you sure you want to Logout?")
            setPositiveButton("Yes") { _, _ ->
                logout()
            }
            setNegativeButton("No", null)
            create()
            show()
        }
    }
}