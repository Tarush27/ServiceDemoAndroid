package boundService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class MyBoundService : Service() {

    private fun generateIntegerRandomNo(): Int {
        val random = Random()
        return random.nextInt(100)
    }

    private val myBinder = MyBinder()
    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    inner class MyBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }
}