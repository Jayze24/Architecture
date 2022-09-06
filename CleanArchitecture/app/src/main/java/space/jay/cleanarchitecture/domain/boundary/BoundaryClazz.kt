package space.jay.cleanarchitecture.domain.boundary

import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.domain.entity.Clazz

interface BoundaryClazz {

    fun getFlowListClazz(name: String?): Flow<List<Clazz>>
    suspend fun insert(data: Clazz): Long // index 반환
    suspend fun delete(vararg id: Long): Int // 삭제된 행의 갯수 반환

    suspend fun addStudent(idClass: Long, idStudent: List<Long>)

}