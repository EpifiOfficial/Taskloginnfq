package com.jmartinez.taskloginnfq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.jmartinez.taskloginnfq.databinding.FragmentHomeBinding
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.network.UserApi
import com.jmartinez.taskloginnfq.repository.UserRepository
import com.jmartinez.taskloginnfq.response.UserResponse
import com.jmartinez.taskloginnfq.ui.base.BaseFragment
import com.jmartinez.taskloginnfq.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding,UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressbar.visible(true)

        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Success->{
                    updateUI(it.value)

                }
                is Resource.loading->{

                }
                is Resource.Failure->{

                }
            }
        })
        binding.BtnLogout.setOnClickListener {
            logout()
        }
    }



    override fun getViewModel() = HomeViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): UserRepository {
        val token  = runBlocking { userPreferences.accessToken.first() }
        val api  = remoteDataSource.buildApi(UserApi::class.java,token)
        return UserRepository(api)
    }
    private fun updateUI(user: UserResponse) {

        with(binding){
            Glide.with(this@HomeFragment).load(user.image.toString()).into(IvUser);

            TvName.text = user.firstName
            TvAddress.text = user.address
            TvPhoneNumber.text = user.phone
            binding.progressbar.visibility = View.GONE


        }
    }

}