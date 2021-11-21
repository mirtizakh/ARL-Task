package com.task.sample.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?) =
    if (coroutineScope == null) this.viewModelScope
    else coroutineScope