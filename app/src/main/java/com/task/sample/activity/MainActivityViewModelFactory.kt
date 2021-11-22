package com.task.sample.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.sample.data.db.DatabaseInterface
import com.task.sample.data.network.api_call.session.ISessionManager

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory (private var sessionManager: ISessionManager,
                                    private var databaseInterface: DatabaseInterface
                                    ): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(sessionManager,databaseInterface) as T
    }
}