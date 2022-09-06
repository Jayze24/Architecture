package space.jay.cleanarchitecture.module.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.jay.cleanarchitecture.data.repository.retrofit.NetworkSchool
import space.jay.cleanarchitecture.data.repository.retrofit.service.ServiceUnsplash
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ModuleNetwork {

    @Singleton
    @Provides
    fun provideNetwork(): NetworkSchool {
        return NetworkSchool()
    }

    @Provides
    fun providerServiceUnsplash(networkSchool: NetworkSchool): ServiceUnsplash {
        return networkSchool.initApi("https://api.unsplash.com/", ServiceUnsplash::class.java)
    }
}