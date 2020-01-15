package com.ted.smartscheduler.api

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSchedulers {
    companion object {
        fun ui() = run { AndroidSchedulers.mainThread() }
        fun io() = run { Schedulers.io() }
        fun computation() = run { Schedulers.computation() }
    }
}