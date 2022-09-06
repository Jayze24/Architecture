package space.jay.cleanarchitecture.domain.useCase.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser

class UseCaseDeleteUser(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryUser,
) {
    suspend operator fun invoke(listId: List<Long>) = withContext(dispatcher) {
        repository.delete(listId = listId)
    }
}