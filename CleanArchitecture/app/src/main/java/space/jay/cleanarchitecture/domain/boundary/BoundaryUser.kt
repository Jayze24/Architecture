package space.jay.cleanarchitecture.domain.boundary

import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.domain.entity.user.Professor
import space.jay.cleanarchitecture.domain.entity.user.TypeUser
import space.jay.cleanarchitecture.domain.entity.user.User

interface BoundaryUser {

    fun getFlowListProfessor(name: String?, typeUser: Int = TypeUser.PROFESSOR.value): Flow<List<Professor>>
    fun getFlowProfessor(id: Long, typeUser: Int = TypeUser.PROFESSOR.value): Flow<Professor>

    suspend fun insert(data: User): Long
    suspend fun delete(listId: List<Long>): Int
}