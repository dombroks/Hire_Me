package com.dom_broks.hireme.worker

import android.content.Context
import android.widget.Toast
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.impl.model.Dependency
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@HiltWorker

class DeleteWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    workerDependency: WorkerDependency

) :
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