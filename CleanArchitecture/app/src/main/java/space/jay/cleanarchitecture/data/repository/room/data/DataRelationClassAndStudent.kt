package space.jay.cleanarchitecture.data.repository.room.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Keep
@Entity(
    tableName = "relationClassAndStudent",
    foreignKeys = [
        ForeignKey(
            entity = DataClazz::class,
            parentColumns = ["id"],
            childColumns = ["idClass"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DataUser::class,
            parentColumns = ["id"],
            childColumns = ["idStudent"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class DataRelationClassAndStudent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val idClass: Long,
    val idStudent: Long,
)
