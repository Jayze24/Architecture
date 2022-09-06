package space.jay.cleanarchitecture.module.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.jay.cleanarchitecture.data.repository.RepositoryClazz
import space.jay.cleanarchitecture.data.repository.RepositoryUser
import space.jay.cleanarchitecture.data.repository.RepositoryUnsplash
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz
import space.jay.cleanarchitecture.domain.boundary.BoundaryLogin
import space.jay.cleanarchitecture.domain.boundary.BoundaryUnsplash
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser

@InstallIn(SingletonComponent::class)
@Module
abstract class ModuleRepository {

    @Binds
    abstract fun bindBoundaryLogin(repositoryUser: RepositoryUser): BoundaryLogin

    @Binds
    abstract fun bindBoundaryUser(repositoryUser: RepositoryUser): BoundaryUser

    @Binds
    abstract fun bindBoundaryClazz(repositoryClazz: RepositoryClazz): BoundaryClazz

    @Binds
    abstract fun bindBoundaryUnsplash(repositoryUnsplash: RepositoryUnsplash): BoundaryUnsplash
}
