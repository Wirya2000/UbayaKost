package id.ubaya.a160419033_ubayakost.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.FragmentProfileBinding
import id.ubaya.a160419033_ubayakost.model.User
import id.ubaya.a160419033_ubayakost.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileUpdateCLickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile,
            container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.listener = this

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            dataBinding.user = it
        }
    }

    override fun onUpdateClick(v: View, obj: User) {
        viewModel.update(obj.name, obj.hometown, obj.phone_number)
        Toast.makeText(v.context, "Profile updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}