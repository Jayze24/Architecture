package space.jay.cleanarchitecture.domain.useCase.clazz

import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz

class UseCaseGetFlowClazzInfo(
    private val repository: BoundaryClazz,
) {
    operator fun invoke(id: Long) = repository.getFlowClazz(id = id)
}