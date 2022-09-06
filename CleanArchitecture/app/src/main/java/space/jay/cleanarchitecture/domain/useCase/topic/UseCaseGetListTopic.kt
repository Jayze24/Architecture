package space.jay.cleanarchitecture.domain.useCase.topic

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.domain.boundary.BoundaryUnsplash
import space.jay.cleanarchitecture.domain.entity.Topic
import space.jay.cleanarchitecture.module.hilt.DispatcherIO
import javax.inject.Inject

// hilt test
// ModuleUseCase 에 만들지 안고 여기다 붙일 경우 잘 작동하는지 테스트
// 원래 use case 에 framework 나 library 가 붙으면 안되기 때문에 여기에 hilt 를 붙이면 안된다
@ViewModelScoped
class UseCaseGetListTopic @Inject constructor(
    @DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryUnsplash,
) {

    suspend operator fun invoke(page: Int): WrapperResult<List<Topic>?> = withContext(dispatcher) {
        repository.getListTopic(page)
    }
}