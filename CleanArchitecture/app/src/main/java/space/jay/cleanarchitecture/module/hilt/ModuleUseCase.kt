package space.jay.cleanarchitecture.module.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazzStudent
import space.jay.cleanarchitecture.domain.boundary.BoundaryLogin
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseAddStudent
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseDeleteClazz
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowClazzInfo
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowListClazz
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseGetFlowListClazzStudent
import space.jay.cleanarchitecture.domain.useCase.clazz.UseCaseInsertClazz
import space.jay.cleanarchitecture.domain.useCase.profess.UseCaseGetFlowListProfessor
import space.jay.cleanarchitecture.domain.useCase.profess.UseCaseGetFlowProfessor
import space.jay.cleanarchitecture.domain.useCase.student.UseCaseGetFlowListStudent
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseDeleteUser
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseInsertUser
import space.jay.cleanarchitecture.domain.useCase.user.UseCaseLogin

@InstallIn(ViewModelComponent::class)
@Module
object ModuleUseCase {

    @Provides
    fun provideUseCaseAddStudent(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryClazzStudent,
    ): UseCaseAddStudent = UseCaseAddStudent(dispatcher, repository)

    @Provides
    fun provideUseCaseDeleteClazz(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryClazz,
    ): UseCaseDeleteClazz = UseCaseDeleteClazz(dispatcher, repository)

    @Provides
    fun provideUseCaseGetFlowListClazz(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryClazz,
    ): UseCaseGetFlowListClazz = UseCaseGetFlowListClazz(dispatcher, repository)

    @Provides
    fun provideUseCaseInsertClazz(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryClazz,
    ): UseCaseInsertClazz = UseCaseInsertClazz(dispatcher, repository)

    @Provides
    fun provideUseCaseGetFlowClazzInfo(
        repository: BoundaryClazz
    ) : UseCaseGetFlowClazzInfo = UseCaseGetFlowClazzInfo(repository = repository)

    @Provides
    fun provideUseCaseGetFlowClazzStudent(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryClazzStudent
    ) : UseCaseGetFlowListClazzStudent = UseCaseGetFlowListClazzStudent(dispatcher, repository)

    @Provides
    fun provideUseCaseGetFlowListProfessor(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryUser,
    ): UseCaseGetFlowListProfessor = UseCaseGetFlowListProfessor(dispatcher, repository)

    @Provides
    fun provideUseCaseGetFlowProfessor(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryUser,
    ): UseCaseGetFlowProfessor = UseCaseGetFlowProfessor(dispatcher, repository)

    @Provides
    fun provideUseCaseGetFlowListStudent(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryUser,
    ): UseCaseGetFlowListStudent = UseCaseGetFlowListStudent(dispatcher, repository)

    @Provides
    fun provideUseCaseInsertUser(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryUser,
    ): UseCaseInsertUser = UseCaseInsertUser(dispatcher, repository)

    @Provides
    fun providerUseCaseDeleteUser(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryUser,
    ): UseCaseDeleteUser = UseCaseDeleteUser(dispatcher, repository)

    @Provides
    fun provideUseCaseLogin(
        @DispatcherIO dispatcher: CoroutineDispatcher,
        repository: BoundaryLogin,
    ): UseCaseLogin = UseCaseLogin(dispatcher, repository)
}