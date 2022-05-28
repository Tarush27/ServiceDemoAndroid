package jobIntentService

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlin.random.Random

private const val TAG = "MyJobService"
private const val TIME_SLEEP_MILLISECONDS: Long = 1000

class MyJobService : JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        performBackgroundWork(p0)
        return false
    }

    private fun performBackgroundWork(p0: JobParameters?) {

        Log.d(TAG, "performBackgroundWork: Random number is : ${Random.nextInt()}")
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "job cancelled before completion")

        return false
    }
}