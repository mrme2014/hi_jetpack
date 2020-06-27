package org.devio.`as`.proj.hi_jetpack.workmanager

import android.content.Context
import android.text.TextUtils
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadFileWorker(
    context: Context,
    workerParams: WorkerParameters
) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val inputData = inputData
        val filePath = inputData.getString("file")
        val fileUrl: String = "文件上传后得到的url"//FileUploadMgr.upload(filePath)
        return if (TextUtils.isEmpty(fileUrl)) {
            Result.failure()
        } else {
            val outputData =
                Data.Builder().putString("fileUrl", fileUrl)
                    .build()
            Result.success(outputData)
        }
    }
}
