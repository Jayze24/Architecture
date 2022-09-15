package space.jay.cleanarchitecture.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.jay.cleanarchitecture.data.repository.room.dao.DaoClazz
import space.jay.cleanarchitecture.data.repository.room.dao.DaoUser
import space.jay.cleanarchitecture.data.repository.room.data.DataClazz
import space.jay.cleanarchitecture.data.repository.room.data.DataRelationClassAndStudent
import space.jay.cleanarchitecture.data.repository.room.data.DataUser
import space.jay.cleanarchitecture.domain.entity.user.TypeUser

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        DataClazz::class,
        DataUser::class,
        DataRelationClassAndStudent::class
    ]
)
@TypeConverters(Converters::class)
abstract class DatabaseSchool : RoomDatabase() {

    abstract fun daoClazz(): DaoClazz
    abstract fun daoUser(): DaoUser

    companion object {
        @Volatile
        private var instance: DatabaseSchool? = null

        fun getDataBase(contextApplication: Context, scope: CoroutineScope): DatabaseSchool =
            instance
            ?: synchronized(this) {
                instance = Room.databaseBuilder(contextApplication, DatabaseSchool::class.java, "school")
                    .addCallback(CallbackDatabaseMain(scope = scope))
                    .build()
                instance!!
            }

        private class CallbackDatabaseMain(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // db 만들어졌을 때 해야할 작업 있으면 여기서 할 것
                instance?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        initUser(database.daoUser())
                        initClazz(database.daoClazz())
                    }
                }
            }
        }

        suspend fun initUser(daoUser: DaoUser) {
            // db 만들어졌을 때 기본적으로 넣을 데이터 있으면 여기서 넣기
            daoUser.insert(DataUser(name = "테스터 교수",
                email = "test@test.com",
                password = "aaaaaa",
                type = TypeUser.PROFESSOR,
                salary = 8000000))
            daoUser.insert(DataUser(name = "테스터 학생1",
                email = "test1@test.com",
                password = "aaaaaa",
                type = TypeUser.STUDENT,
                grade = 1))
            daoUser.insert(DataUser(name = "테스터 학생2",
                email = "test2@test.com",
                password = "aaaaaa",
                type = TypeUser.STUDENT,
                grade = 2))
        }

        suspend fun initClazz(daoClazz: DaoClazz) {
            daoClazz.insert(DataClazz(name = "Dream Class", capacity = 30))
        }
    }

}