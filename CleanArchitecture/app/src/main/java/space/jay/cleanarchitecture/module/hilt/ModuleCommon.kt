package space.jay.cleanarchitecture.module.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherIO

@Module
@InstallIn(SingletonComponent::class)
object ModuleCoroutines {

    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}

