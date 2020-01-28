package com.ted.fluffy

import android.os.Bundle
import android.util.Log
import com.ted.fluffy.api.ApiManager
import com.ted.fluffy.api.RxSchedulers
import com.ted.fluffy.core.BaseActivity
import io.reactivex.disposables.Disposable

class MainActivity : BaseActivity() {

    var disposable: Disposable? = null
    private lateinit var locationClient: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = ApiManager().from()
        disposable = api.findPath(1f, 1f, 2f, 2f)
            .subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.ui())
            .subscribe(
                { it.toString() },
                { Log.e("TED", "error : $it") }
            )
    }
}
