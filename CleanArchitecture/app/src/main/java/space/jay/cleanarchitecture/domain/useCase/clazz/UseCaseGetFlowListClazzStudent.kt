package space.jay.cleanarchitecture.domain.useCase.clazz

import space.jay.cleanarchitecture.domain.boundary.BoundaryClazzStudent

class UseCaseGetFlowListClazzStudent(
    private val repository: BoundaryClazzStudent,
) {
    operator fun invoke(idClazz: Long) = repository.getFlowListClazzStudent(idClazz)
}