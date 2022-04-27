package com.jmartinez.taskloginnfq.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jmartinez.taskloginnfq.R
import com.jmartinez.taskloginnfq.databinding.FragmentLoginBinding
import com.jmartinez.taskloginnfq.enable
import com.jmartinez.taskloginnfq.network.AuthApi
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.repository.AuthRepository
import com.jmartinez.taskloginnfq.startNewActivity
import com.jmartinez.taskloginnfq.ui.base.BaseFragment
import com.jmartinez.taskloginnfq.ui.home.HomeActivity
import com.jmartinez.taskloginnfq.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.progressbar.visible(false)
        binding.BtnLogin.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(false)
            when (it){
                is Resource.Success->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                        viewModel.saveAuthToken(it.value.token,it.value.refreshToken)
                        requireActivity().startNewActivity(HomeActivity::class.java)


                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.EtPassword.addTextChangedListener {
            val username = binding.EtEmail.text.toString().trim()
            binding.BtnLogin.enable(username.isNotEmpty()&& it.toString().isNotEmpty())
        }
        binding.BtnLogin.setOnClickListener {
            val username = binding.EtEmail.text.toString().trim()
            val password = binding.EtPassword.text.toString().trim()
            viewModel.login(username,password)
            binding.progressbar.visible(true)



        }
    }

    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)






}