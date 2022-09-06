package space.jay.cleanarchitecture.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import space.jay.cleanarchitecture.data.repository.room.DatabaseSchool

@HiltAndroidApp
class BaseApplication : Application() {

}