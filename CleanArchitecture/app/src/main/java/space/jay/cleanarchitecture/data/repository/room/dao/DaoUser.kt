package space.jay.cleanarchitecture.data.repository.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.data.repository.room.data.DataUser

@Dao
interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(data: DataUser): Long

    @Query("DELETE FROM user WHERE id in (:listId)")
    suspend fun delete(listId: List<Long>): Int

    /**
     * 사용자 로그인
     **/
    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): DataUser?

    /**
     * 교수
     **/
    @Query("SELECT * FROM user WHERE type = :typeUser AND name LIKE '%' || :name || '%' ")
    fun getFlowListProfessor(name: String, typeUser: Int): Flow<List<DataUser>>

    @Query("SELECT * FROM user WHERE id == :id And type == :typeUser")
    fun getFlowProfessor(id: Long, typeUser: Int): Flow<DataUser>

//    @Query("SELECT * FROM professor JOIN major ON professor.idMajor = major.id WHERE id = :id")
//    override suspend fun getProfessor(id: Long): Map<EntityProfessor, EntityMajor>
//
//    @Query("SELECT * FROM professor JOIN major ON professor.idMajor = major.id WHERE name LIKE '%' || :name || '%' ")
//    override suspend fun getListProfessor(name: String?): Map<EntityProfessor, EntityMajor>
}