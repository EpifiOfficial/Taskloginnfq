package com.jmartinez.taskloginnfq.ui.base

import androidx.lifecycle.ViewModel
import com.jmartinez.taskloginnfq.network.UserApi
import com.jmartinez.taskloginnfq.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository :BaseRepository

):ViewModel() {

    suspend fun logout(api:UserApi)= withContext(Dispatchers.IO){repository.logout(api)}
}