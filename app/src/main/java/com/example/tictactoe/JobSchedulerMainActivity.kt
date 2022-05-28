package com.example.tictactoe

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import jobIntentService.MyJobService

class JobSchedulerMainActivity : AppCompatActivity() {
    companion object {
        const val JOB_ID = 123
        const val MainActivity3 = "MainActivity3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.job_scheduler_main_activity)
        val scheduleJobBtn: Button = findViewById(R.id.schedule_job_btn)
        val cancelJobBtn: Button = findViewById(R.id.cancel_job_btn)
        scheduleJobBtn.setOnClickListener {
            scheduleJob()
        }
        cancelJobBtn.setOnClickListener {
            cancelJob()
        }
    }

    private fun cancelJob() {
        val jobScheduler: JobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(JOB_ID)
        Log.d(MainActivity3, "Job Cancelled")
    }

    private fun scheduleJob() {
        val jobScheduler: JobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(this, MyJobService::class.java)
        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setPeriodic(15*60*1000).build()
        val result = jobScheduler.schedule(jobInfo)
        if (result == JobScheduler.RESULT_SUCCESS) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
        }

    }
}