package com.bus.map.demo.client

import `in`.redbus.android.rblogger.client.OSRMApiClient
import android.content.Context
import androidx.work.*
import com.bus.map.demo.osrmlistener.OSRMRouteListener
import com.bus.map.demo.osrmmodels.OsrmItem
import com.example.workmanager.OSRMPeriodicWorkManager
import java.util.concurrent.TimeUnit

class MapWorkManager: OSRMRouteListener {


    private val OSRM_TAG: String= "OSRMWorkManager"


    companion object {
        var rbLogger: MapWorkManager? = null

        fun getInstance(): MapWorkManager {
            if (rbLogger == null) {
                rbLogger =
                    MapWorkManager()
                return rbLogger as MapWorkManager
            } else
                return rbLogger as MapWorkManager
        }
    }

    /**
     * This method is used to initialize the work manager
     */
     fun initPeriodicWorkManager(mContext : Context, timeInterval : Long, timeUnit: TimeUnit){
         var mRedBusApiClient: OSRMApiClient? = null
        mRedBusApiClient = OSRMApiClient(mContext)
        mRedBusApiClient!!.callRedBusLoggerApi(this,this)

      //  createWorkManager(mContext, PERIODIC_WM_TAG, timeInterval, timeUnit)
    }

    fun initTimeBoundWorkManager(timeInterval : Long, timeUnit: TimeUnit) {
      //  createWorkManager(RETRY_TAG, timeInterval, timeUnit)
    }

    fun createWorkManager(mContext: Context,
        tag: String,
        timeInterval: Long,
        timeUnit: TimeUnit
    ) {

        val constraints : Constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .setRequiresDeviceIdle(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val workRequest : PeriodicWorkRequest = PeriodicWorkRequest
            .Builder(OSRMPeriodicWorkManager::class.java,timeInterval, timeUnit)
            .setConstraints(constraints)
            .addTag(tag)
            .build()
        WorkManager.getInstance(mContext).enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.KEEP,workRequest)
    }

    override fun onResult(result: OsrmItem?) {




    }

    override fun onFailure(e: Throwable?) {
    }




}