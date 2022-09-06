package space.jay.cleanarchitecture.data.repository.room

import androidx.room.TypeConverter
import space.jay.cleanarchitecture.domain.entity.user.TypeUser

class Converters {
    @TypeConverter
    fun userToInt(value: TypeUser): Int {
        return value.value
    }

    @TypeConverter
    fun intToUser(value: Int): TypeUser {
        return when (value) {
            TypeUser.PROFESSOR.value -> TypeUser.PROFESSOR
            else -> TypeUser.STUDENT
        }
    }
}