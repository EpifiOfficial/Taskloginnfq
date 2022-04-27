package com.jmartinez.taskloginnfq.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jmartinez.taskloginnfq.repository.AuthRepository
import com.jmartinez.taskloginnfq.repository.BaseRepository
import com.jmartinez.taskloginnfq.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository:BaseRepository

):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository)as T
            else->throw IllegalArgumentException("View Model class not found")

        }


    }



}