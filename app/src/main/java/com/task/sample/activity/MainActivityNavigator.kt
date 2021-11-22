package com.task.sample.activity

import com.task.sample.ui.base.BaseNavigator

interface MainActivityNavigator : BaseNavigator{
    fun logoutSuccessfully()
    fun setProductCountValue(count: Int)
}