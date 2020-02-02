package com.yoyo.cinema.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {
    var lastJob: Job? = null

}