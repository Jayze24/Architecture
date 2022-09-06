package space.jay.cleanarchitecture.domain.useCase.clazz

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz

class UseCaseGetFlowListClazz(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryClazz,
) {

    private val _flowName = MutableStateFlow<String?>("")

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke() = _flowName
        .distinctUntilChanged { old, new -> old == new }
        .flatMapLatest { repository.getFlowListClazz(name = it) }

    suspend fun search(name: String?) = withContext(dispatcher) {
        _flowName.emit(name)
    }
}