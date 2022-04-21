package startedService

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyOwnService : Service() {
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Services created", Toast.LENGTH_SHORT).show()
        Log.i("MyOwnService", "Service has created")
    }

    // starting the service by implementing Toast.
    // on start command returns some int value. it provides two major modes of operation.
    // for starting the services.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Services started", Toast.LENGTH_SHORT).show()
        Log.i("MyOwnService", "Service has started")


        /*
         tells the system to restart the service after it has been killed.
         again onStartCommand fun is invoked with a null intent.

         for those services that are explicitly started.
         */

        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Services stopped", Toast.LENGTH_SHORT).show()
        Log.i("MyOwnService", "Service has stopped")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}