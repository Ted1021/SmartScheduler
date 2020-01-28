package com.ted.fluffy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ted.fluffy.api.ApiManager
import com.ted.fluffy.api.RxSchedulers
import com.ted.fluffy.core.BaseActivity
import io.reactivex.disposables.Disposable

class MainActivity : BaseActivity() {
    companion object {
        val TAG = MainActivity.javaClass.simpleName
    }

    var disposable: Disposable? = null
    private lateinit var locationClient: FusedLocationProviderClient

    private var startX: Float = 0f
    private var startY: Float = 0f

    private var endX: Float = 37.33f
    private var endY: Float = 126.58f

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.result)

        locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation.addOnSuccessListener {
            startX = it.altitude.toFloat()
            startY = it.longitude.toFloat()
        }
        locationClient.lastLocation.addOnFailureListener { Log.e(TAG, it.localizedMessage) }

        val api = ApiManager().from()
        disposable = api.findPath(startX, startY, endX, endY)
            .subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.ui())
            .subscribe(
                { tvResult.setText("#current location\nstartX:$startX / startY:$startY\nodsayResult:${it.toString()}") },
                { Log.e("TED", "error : $it") }
            )
    }
}
