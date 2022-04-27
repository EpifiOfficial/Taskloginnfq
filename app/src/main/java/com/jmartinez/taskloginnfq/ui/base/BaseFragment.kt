package com.jmartinez.taskloginnfq.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.jmartinez.taskloginnfq.network.RemoteDataSource
import com.jmartinez.taskloginnfq.network.UserApi
import com.jmartinez.taskloginnfq.repository.BaseRepository
import com.jmartinez.taskloginnfq.response.UserPreferences
import com.jmartinez.taskloginnfq.startNewActivity
import com.jmartinez.taskloginnfq.ui.auth.AuthActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM:BaseViewModel,B:ViewBinding,R:BaseRepository> :Fragment(){
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater,container)

        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())
        lifecycleScope.launch { userPreferences.accessToken.first() }
        return binding.root

    }
    fun logout() = lifecycleScope.launch {
        val accessToken = userPreferences.accessToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java,accessToken)
        viewModel.logout(api)

        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)



    }
    abstract fun getViewModel():Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?):B
    abstract fun getFragmentRepository():R



}