package uz.gita.contactappwithroom

import android.app.Application
import uz.gita.contactappwithroom.db.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}