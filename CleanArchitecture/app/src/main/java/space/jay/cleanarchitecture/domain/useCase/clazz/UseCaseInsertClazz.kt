package space.jay.cleanarchitecture.domain.useCase.clazz

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz
import space.jay.cleanarchitecture.domain.entity.Clazz

class UseCaseInsertClazz(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryClazz,
) {
    suspend operator fun invoke(data: Clazz) = withContext(dispatcher) {
        repository.insert(data = data)
    }
}