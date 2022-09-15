package space.jay.cleanarchitecture.domain.useCase.student

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser

class UseCaseGetFlowListStudent(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryUser,
) {

    private val _flowName = MutableStateFlow<String?>(null)

    operator fun invoke() = _flowName
        .distinctUntilChanged { old, new -> old == new }
        .flatMapLatest { repository.getFlowListStudent(it) }

    suspend fun search(name: String?) = withContext(dispatcher) {
        _flowName.emit(name)
    }
}