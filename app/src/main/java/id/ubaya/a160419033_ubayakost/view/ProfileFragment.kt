package id.ubaya.a160419033_ubayakost.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    companion object {
        var SHARED_PROFILE_NAME = "SHARED_PROFILE_NAME"
        var SHARED_PROFILE_HOMETOWN = "SHARED_PROFILE_HOMETOWN"
        var SHARED_PROFILE_PHONE = "SHARED_PROFILE_PHONE"
    }
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var name = sharedPreferences.getString(SHARED_PROFILE_NAME, "")
        var hometown = sharedPreferences.getString(SHARED_PROFILE_HOMETOWN, "")
        var phone = sharedPreferences.getString(SHARED_PROFILE_PHONE, "")

        viewModel.fetch(name, hometown, phone)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            val profile = it
                profile?.let {
                    editName.setText(it.name)
                    editHomeTown.setText(it.hometown)
                    editPhone.setText(it.phoneNumber)
                }
            buttonUpdate.setOnClickListener{
                val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
                sharedPreferences.edit {
                    putString(SHARED_PROFILE_NAME, editName.text?.toString())
                    putString(SHARED_PROFILE_HOMETOWN, editHomeTown.text?.toString())
                    putString(SHARED_PROFILE_PHONE, editPhone.text?.toString())
                }
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Your profile has been updated")
                builder.setPositiveButton("OK", null)
                builder.create().show()
            }
        }
    }
}