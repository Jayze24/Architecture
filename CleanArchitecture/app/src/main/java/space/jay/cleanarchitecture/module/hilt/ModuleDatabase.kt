package space.jay.cleanarchitecture.module.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import space.jay.cleanarchitecture.data.repository.room.DatabaseSchool
import space.jay.cleanarchitecture.data.repository.room.dao.DaoClazz
import space.jay.cleanarchitecture.data.repository.room.dao.DaoUser
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ModuleDatabase {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext contextApplication: Context): DatabaseSchool {
        return DatabaseSchool.getDataBase(contextApplication, CoroutineScope(SupervisorJob() + Dispatchers.IO))
    }

    @Provides
    fun providerClazz(databaseSchool: DatabaseSchool): DaoClazz {
        return databaseSchool.daoClazz()
    }

    @Provides
    fun providerUser(databaseSchool: DatabaseSchool): DaoUser {
        return databaseSchool.daoUser()
    }
}