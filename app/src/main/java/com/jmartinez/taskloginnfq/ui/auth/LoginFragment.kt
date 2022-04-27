package com.jmartinez.taskloginnfq.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jmartinez.taskloginnfq.R
import com.jmartinez.taskloginnfq.databinding.FragmentLoginBinding
import com.jmartinez.taskloginnfq.network.AuthApi
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.repository.AuthRepository
import com.jmartinez.taskloginnfq.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resource.Success->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.BtnLogin.setOnClickListener {
            val username = binding.EtEmail.text.toString().trim()
            val password = binding.EtPassword.text.toString().trim()
            viewModel.login(username,password)
        }
    }

    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))






}