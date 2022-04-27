package com.jmartinez.taskloginnfq.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.repository.UserRepository
import com.jmartinez.taskloginnfq.response.UserResponse
import com.jmartinez.taskloginnfq.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository:UserRepository
):BaseViewModel(repository) {

    private val _user:MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user:LiveData<Resource<UserResponse>>
    get() = _user

    fun getUser()= viewModelScope.launch{
        _user.value = repository.getUser()
        _user.value = Resource.loading

    }

}