package space.jay.cleanarchitecture.data.repository

import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.data.repository.room.dao.DaoClazz
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.data.repository.room.data.DataRelationClassAndStudent
import space.jay.cleanarchitecture.domain.boundary.BoundaryClazz
import space.jay.cleanarchitecture.domain.entity.Clazz
import javax.inject.Inject
import javax.inject.Singleton


class RepositoryClazz @Inject constructor(
    private val dataSourceClazz: DaoClazz,
) : BoundaryClazz {

    override fun getFlowListClazz(name: String?): Flow<List<Clazz>> = dataSourceClazz.getFlowListClazz(name = name)

    override suspend fun insert(data: Clazz): Long {
        return dataSourceClazz.insert(
            data = DataClazz(
                id = data.id,
                name = data.name,
                capacity = data.capacity
            )
        )
    }

    override suspend fun delete(vararg id: Long): Int {
        return dataSourceClazz.delete(arrayId = id)
    }

    override suspend fun addStudent(idClass: Long, idStudent: List<Long>) {
        val data = Array<DataRelationClassAndStudent>(idStudent.size) {
            DataRelationClassAndStudent(idClass = idClass, idStudent = idStudent[it])
        }
        return dataSourceClazz.addStudent(data = data)
    }


}