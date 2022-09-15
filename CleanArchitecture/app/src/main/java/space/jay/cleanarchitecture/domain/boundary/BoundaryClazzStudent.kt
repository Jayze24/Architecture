package space.jay.cleanarchitecture.domain.boundary

import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.domain.entity.user.Student

interface BoundaryClazzStudent {

    suspend fun addStudent(idClass: Long, idStudent: List<Long>)
    fun getFlowListClazzStudent(idClass: Long): Flow<List<Student>>
}