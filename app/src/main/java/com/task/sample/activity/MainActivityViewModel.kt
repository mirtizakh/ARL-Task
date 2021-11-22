package com.task.sample.activity

import com.task.sample.data.db.DatabaseInterface
import com.task.sample.data.network.api_call.session.ISessionManager
import com.task.sample.ui.base.BaseViewModel

class MainActivityViewModel(private var sessionManager: ISessionManager,
                            private var databaseInterface: DatabaseInterface
) : BaseViewModel<MainActivityNavigator>() {
    // region VARIABLES

    // end region VARIABLES

    // region PUBLIC methods

    // end region PUBLIC methods

    // region PRIVATE methods

    // end region PRIVATE methods
}