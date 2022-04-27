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
import com.bumptech.glide.Glide
import com.jmartinez.taskloginnfq.*
import com.jmartinez.taskloginnfq.databinding.FragmentLoginBinding
import com.jmartinez.taskloginnfq.network.AuthApi
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.repository.AuthRepository
import com.jmartinez.taskloginnfq.ui.base.BaseFragment
import com.jmartinez.taskloginnfq.ui.home.HomeActivity
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load("https://placeimg.com/80/80/tech").into(binding.IvIcon);

        binding.BtnLogin.enable(false)


        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visibility = View.GONE

            when (it){
                is Resource.Success->{
                    lifecycleScope.launch {
                        binding.progressbar.visibility = View.GONE
                        viewModel.saveAuthToken(it.value.token,it.value.refreshToken)
                        requireActivity().startNewActivity(HomeActivity::class.java)

                    }

                }
                is Resource.Failure ->handleApiError(it){
                    binding.progressbar.visibility = View.GONE
                    login()


                }
            }
        })
        binding.EtPassword.addTextChangedListener {
            val username = binding.EtEmail.text.toString().trim()
            binding.BtnLogin.enable(username.isNotEmpty()&& it.toString().isNotEmpty())
        }
        binding.BtnLogin.setOnClickListener {
        login()

        }
    }

    private fun login(){
        val username = binding.EtEmail.text.toString().trim()
        val password = binding.EtPassword.text.toString().trim()
        viewModel.login(username,password)
        binding.progressbar.visible(true)

    }
    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)






}