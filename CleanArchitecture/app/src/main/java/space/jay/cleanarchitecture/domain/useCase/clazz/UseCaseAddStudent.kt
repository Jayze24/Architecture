package space.jay.cleanarchitecture.domain.useCase.clazz

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazzStudent
import space.jay.cleanarchitecture.domain.entity.Clazz

class UseCaseAddStudent(
    private val dispatcher: CoroutineDispatcher,
    private val repository: BoundaryClazzStudent,
) {
    suspend operator fun invoke(idClass: Long, idStudent: List<Long>) = withContext(dispatcher) {
        repository.addStudent(idClass, idStudent)
    }
}