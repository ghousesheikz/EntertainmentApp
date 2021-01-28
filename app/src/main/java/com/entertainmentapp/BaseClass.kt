package com.entertainmentapp

import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import com.entertainmentapp.Locationtracker.BaseActivityLocation


abstract class BaseClass : BaseActivityLocation() {

    var request: OneTimeWorkRequest? = null
    var mLatitude:Double?=0.0
    var mLongitude : Double?=0.0

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        request = OneTimeWorkRequestBuilder<WorkManager>().setConstraints(constraints).build()
    }

    override fun locationFetched(
        mLocation: Location?,
        oldLocation: Location?,
        time: String?,
        locationProvider: String?
    ) {
        super.locationFetched(
            mLocation, oldLocation, time, locationProvider
        )
        mLatitude = mLocation!!.latitude
        mLongitude = mLocation!!.longitude

        Toast.makeText(
            application,
            "Lat : " + mLocation!!.getLatitude() + " Lng : " + mLocation.getLongitude(),
            Toast.LENGTH_SHORT
        ).show()

    }
}