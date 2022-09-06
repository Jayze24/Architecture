package space.jay.cleanarchitecture.domain.useCase.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser
import space.jay.cleanarchitecture.domain.entity.user.User

class UseCaseInsertUser(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryUser,
) {
    suspend operator fun invoke(data: User) = withContext(dispatcher) {
        if (data.name.isNotBlank() && data.email.isNotBlank() && data.password.isNotBlank()) {
            repository.insert(data = data)
        }
    }
}