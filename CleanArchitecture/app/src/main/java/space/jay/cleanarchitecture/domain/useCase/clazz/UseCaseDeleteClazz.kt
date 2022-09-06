package space.jay.cleanarchitecture.domain.useCase.clazz

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz

class UseCaseDeleteClazz(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryClazz,
) {
    suspend operator fun invoke(vararg id: Long) = withContext(dispatcher) {
        repository.delete(id = id)
    }
}