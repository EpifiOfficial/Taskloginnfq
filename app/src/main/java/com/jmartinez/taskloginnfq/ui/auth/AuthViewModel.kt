package com.jmartinez.taskloginnfq.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinez.taskloginnfq.network.Resource
import com.jmartinez.taskloginnfq.repository.AuthRepository
import com.jmartinez.taskloginnfq.response.LoginResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

class AuthViewModel(

    private val repository: AuthRepository


):ViewModel() {
    private val _loginResponse :MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse : LiveData<Resource<LoginResponse>>
        get() = _loginResponse


    fun login(
        username : String,
        password : String

    )=viewModelScope.launch{
        _loginResponse.value = repository.login(username,password)

    }
    fun saveAuthToken(token:String,refreshToken:String)=viewModelScope.launch{
        repository.saveAuthToken(token,refreshToken)

    }


}