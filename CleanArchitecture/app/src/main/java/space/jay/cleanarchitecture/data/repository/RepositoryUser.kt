package space.jay.cleanarchitecture.data.repository

import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.data.repository.room.dao.DaoUser
import space.jay.cleanarchitecture.data.repository.room.data.DataUser
import space.jay.cleanarchitecture.domain.boundary.BoundaryLogin
import space.jay.cleanarchitecture.domain.boundary.BoundaryUser
import space.jay.cleanarchitecture.domain.entity.user.Professor
import space.jay.cleanarchitecture.domain.entity.user.Student
import space.jay.cleanarchitecture.domain.entity.user.User
import javax.inject.Inject

class RepositoryUser @Inject constructor(
    private val dataSourceUser: DaoUser,
) : BoundaryUser, BoundaryLogin {

    override suspend fun getUser(email: String, password: String): User? =
        dataSourceUser.getUser(email = email, password = password)

    override fun getFlowListProfessor(name: String?, typeUser: Int): Flow<List<Professor>> =
        dataSourceUser.getFlowListProfessor(
            name = name
                   ?: "",
            typeUser = typeUser
        )

    override fun getFlowProfessor(id: Long, typeUser: Int): Flow<Professor> =
        dataSourceUser.getFlowProfessor(
            id = id,
            typeUser = typeUser
        )

    override fun getFlowListStudent(name: String?, typeUser: Int): Flow<List<Student>> =
        dataSourceUser.getFlowListStudent(
            name = name
                   ?: "",
            typeUser = typeUser
        )

    override suspend fun insert(data: User): Long {
        return dataSourceUser.insert(
            data = DataUser(
                id = data.id,
                name = data.name,
                email = data.email,
                password = data.password,
                type = data.type,
                salary = if (data is Professor) data.salary else 0,
                grade = if (data is Student) data.grade else 0
            )
        )
    }

    override suspend fun delete(listId: List<Long>): Int {
        return dataSourceUser.delete(listId = listId)
    }
}