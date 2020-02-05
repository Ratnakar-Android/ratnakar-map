package `in`.redbus.android.rblogger.client

import android.content.Context
import android.util.Log
import com.bus.map.demo.UserClient
import com.bus.map.demo.osrmlistener.OSRMRouteListener
import com.bus.map.demo.osrmmodels.OsrmItem
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class OSRMApiClient(var mContext: Context?)  {
    private val TAG = "OSRMApiClient"
    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: OSRMApiClient? = null

        @JvmStatic
        fun getInstance(context: Context?): OSRMApiClient {
            return INSTANCE ?: synchronized(this) {
                OSRMApiClient(context).also { INSTANCE = it }
            }
        }
    }
     fun callRedBusLoggerApi(listener: OSRMRouteListener, handleErrorCode: OSRMRouteListener) {


         var app = mContext!!.applicationContext as UserClient

         val networkCall: Single<retrofit2.Response<OsrmItem>> = app.osrmApi!!.osrmRouteData


         networkCall.subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<retrofit2.Response<OsrmItem>> {

             override fun onSubscribe(d: Disposable) {
             }
             override fun onSuccess(response: retrofit2.Response<OsrmItem>) {
                 Log.d(TAG, "onSuccess: toString: " + response.toString())
                 Log.d(TAG, "onSuccess: toString: " + response.body())
                 if (response != null) {
                     listener.onResult(response.body())
                 }
             }
             override fun onError(e: Throwable) {
                 Log.d(TAG, "onSuccess: toString: " + e.toString())
                 //listener.onResult(response.toString())
             }
         });
    }

}