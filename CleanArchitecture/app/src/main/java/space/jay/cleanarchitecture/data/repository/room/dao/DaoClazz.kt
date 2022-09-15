package space.jay.cleanarchitecture.data.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.data.repository.room.data.DataRelationClassAndStudent
import space.jay.cleanarchitecture.data.repository.room.data.DataUser

@Dao
interface DaoClazz {

//    @Query("SELECT * FROM clazz WHERE id = :id")
//    suspend fun getFlowClass(id: Long): Flow<DataClass>

    @Query("SELECT * FROM clazz WHERE name LIKE '%' || :name || '%' ")
    fun getFlowListClazz(name: String?): Flow<List<DataClazz>>

    @Query("SELECT * FROM clazz WHERE id = :id")
    fun getFlowClazz(id: Long): Flow<DataClazz>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(data: DataClazz): Long

    @Query("DELETE FROM clazz WHERE id in (:arrayId)")
    suspend fun delete(arrayId: LongArray): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(vararg data: DataRelationClassAndStudent)

    @Query("SELECT * FROM user INNER JOIN relationClassAndStudent AS re ON re.idClass = :id WHERE user.id = re.idStudent")
    fun getFlowListClassStudent(id: Long): Flow<List<DataUser>>
}