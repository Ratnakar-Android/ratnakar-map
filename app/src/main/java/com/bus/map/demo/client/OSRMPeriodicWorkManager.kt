package com.example.workmanager

import `in`.redbus.android.rblogger.client.OSRMApiClient
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bus.map.demo.osrmlistener.OSRMRouteListener
import com.bus.map.demo.osrmmodels.OSRMDirectionResult
import com.bus.map.demo.osrmmodels.OsrmItem
import com.google.gson.Gson
import io.reactivex.Scheduler
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
/**
 * This class is used to upload the exception to server periodically
 */
class OSRMPeriodicWorkManager(context: Context, params: WorkerParameters) : Worker(context,params),
        OSRMRouteListener {



    override fun onResult(result: OsrmItem?) {

        isSuccess = true

        countDownLatch.countDown()
    }



    override fun onFailure(e: Throwable?) {

        countDownLatch.countDown()
    }

    // private var errorLogs : List<RbLoggerEntity>? = null
    var context : Context? = null
    private var mRedBusApiClient: OSRMApiClient? = null
    private lateinit var countDownLatch:CountDownLatch
    private var isSuccess:Boolean = false

    init {
        this.context = context
        this.countDownLatch = CountDownLatch(1)
    }

    override fun doWork(): Result {

        var retval:Result = Result.failure()

       // fetchExceptionLogFromDB()


           // val gson = Gson()
            //val json = gson.toJson(errorLogs)

            mRedBusApiClient = OSRMApiClient(context)
            mRedBusApiClient!!.callRedBusLoggerApi(this,this)

            try {
                countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        if(isSuccess) {
             retval = Result.success()
            // If work is completed cancel the 30 minute period manager
           // MapWorkManager.getInstance().cancelRetryWorkManager()
        }

        return retval;
    }

//    /**
//     * This method is used to fetch log from room database
//     */
//    private fun fetchExceptionLogFromDB(){
//        val database = RBLoggerDatabase.getDatabase(RbLogAnalytics.context)
//        errorLogs =   database?.rbLoggerDao()?.getAllLogs()
//
//    }

//    override fun onApiSuccess(response: String?) {
//        //Todo- check if work completed or not based in work manager request ID and add timestamp check before deleting logs
//        if(response.equals("200")) {
//            val database = RBLoggerDatabase.getDatabase(RbLogAnalytics.context)
//            database?.rbLoggerDao()?.deleteAllLogs()
//
//            isSuccess = true
//        } else {
//            var timeInterval : Long = 30
//            MapWorkManager.getInstance().initTimeBoundWorkManager(timeInterval, TimeUnit.MINUTES)
//
//        }
//
//        countDownLatch.countDown()
//    }
//
//    override fun OnApiError(response: String?) {
//        countDownLatch.countDown()
//    }


//    /**
//     * This method is used to upload/ log error to server
//     */
//    private fun logExceptionToServer(errorLog: List<RbLoggerEntity>)
//    : Result{
//        val sb = StringBuilder()
//        val httpUrl = "https://abcd"
//        var urlConnection: HttpURLConnection? = null
//        try {
//            val url = URL(httpUrl)
//            urlConnection = url.openConnection() as HttpURLConnection
//            urlConnection.doOutput = true
//            urlConnection.requestMethod = "POST"
//            urlConnection.useCaches = false
//            urlConnection.connectTimeout = 10000
//            urlConnection.readTimeout = 10000
//            urlConnection.setRequestProperty("Content-Type", "application/json")
//            urlConnection.connect()
//
//            val gson = Gson()
//            val json = gson.toJson(errorLog)
//
//            val out =  DataOutputStream(urlConnection.outputStream)
//            out.writeBytes(URLEncoder.encode(json.toString(),"UTF-8"))
//            Log.d("WorkManager" , urlConnection.toString())
//            out.flush ()
//            out.close ()
//            val result = urlConnection.responseCode
//            if (result == HttpURLConnection.HTTP_OK) {
//                val br = BufferedReader(
//                    InputStreamReader(
//                        urlConnection.inputStream, "utf-8"
//                    )
//                )
//                var line = br.readLine()
//                while (line != null) {
//                    sb.append(line)
//                    line = br.readLine()
//                }
//                br.close()
//                return Result.success()
//                Log.d("WorkManager" ,  sb.toString())
//
//            } else {
//                Log.d("WorkManager" ,urlConnection.responseMessage)
//            }
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        } finally {
//            urlConnection?.disconnect()
//        }
//        return Result.failure()
//    }


}