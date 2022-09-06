package space.jay.cleanarchitecture.data.repository.room.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import space.jay.cleanarchitecture.domain.entity.user.Professor
import space.jay.cleanarchitecture.domain.entity.user.Student
import space.jay.cleanarchitecture.domain.entity.user.TypeUser
import space.jay.cleanarchitecture.domain.entity.user.User

@Keep
@Entity(
    tableName = "user",
    indices = [
        Index(value = ["name", "password"]),
        Index(value = ["email"], unique = true)
    ]
)
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    override var name: String,
    override val email: String,
    override val password: String,
    override var type: TypeUser,
    override var salary: Long = 0,
    override var grade: Int = 0,
) : User, Professor, Student
