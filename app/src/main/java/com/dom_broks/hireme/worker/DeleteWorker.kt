package com.dom_broks.hireme.worker

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dom_broks.hireme.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DeleteWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "deleting an item from portfolio"
    }

    override fun doWork(): Result {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(applicationContext, "worker is fine", Toast.LENGTH_LONG).show()
        }

        return Result.success()
    }


}