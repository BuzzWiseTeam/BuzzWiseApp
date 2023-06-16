package com.example.buzzwiseapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.buzzwiseapp.data.ViewModelFactory
import com.example.buzzwiseapp.data.model.UserPreference
import com.example.buzzwiseapp.data.response.DataUser
import com.example.buzzwiseapp.databinding.FragmentProfileBinding
import com.example.buzzwiseapp.ui.auth.LoginActivity
import com.example.buzzwiseapp.ui.home.dataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory(UserPreference.getInstance(requireContext().dataStore))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
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
        setupViewModel()
        setupView()

        return binding.root
    }
    private fun setDetailProfile(prof: DataUser) {
        with(binding) {
            tvName.text = prof.name
        }
    }
    private fun showLoading(value: Boolean) {
        with(binding) {
            tvName.isVisible = value
        }
    }
    private fun setupView() {
        //val userId: String = "Ov0KYbM7gYTHZhV0p6KilBioY5A2"
        //profileViewModel.getProfile(userId as String)
    }
    private fun setupViewModel() {
        view?.let { fragmentView ->
            profileViewModel.detailProfile.observe(viewLifecycleOwner) { detailProfile ->
                setDetailProfile(detailProfile)
            }

            profileViewModel.loadingScreen.observe(viewLifecycleOwner) { isLoading ->
                showLoading(isLoading)
            }
        }
    }
    private fun logout() {
        profileViewModel.logout()
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