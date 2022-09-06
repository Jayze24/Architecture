package space.jay.cleanarchitecture.domain.useCase.profess

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser

class UseCaseGetFlowProfessor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryUser,
) {

    private val _flowId = MutableSharedFlow<Long>()

    operator fun invoke() = _flowId
        .distinctUntilChanged()
        .flatMapLatest { repository.getFlowProfessor(id = it) }

    suspend fun search(id: Long) = withContext(dispatcher) {
        _flowId.emit(id)
    }
}