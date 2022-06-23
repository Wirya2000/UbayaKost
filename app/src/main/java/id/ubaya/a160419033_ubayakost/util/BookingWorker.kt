package id.ubaya.a160419033_ubayakost.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BookingWorker(val context: Context, val params: WorkerParameters)
    :Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper(context)
            .createBookingNotification(
                inputData.getString("title").toString(),
                inputData.getString("message").toString())
        return Result.success()
    }

}