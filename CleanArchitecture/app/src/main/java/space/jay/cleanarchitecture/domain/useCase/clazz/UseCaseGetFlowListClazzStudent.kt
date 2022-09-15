package space.jay.cleanarchitecture.domain.useCase.clazz

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazzStudent

class UseCaseGetFlowListClazzStudent(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryClazzStudent,
) {

    private val _flowClazz = MutableStateFlow<Long?>(null)

    operator fun invoke() = _flowClazz
        .filterNotNull()
        .flatMapLatest { repository.getFlowListClazzStudent(it) }

    suspend fun search(idClazz: Long) = withContext(dispatcher) {
        _flowClazz.emit(idClazz)
    }
}