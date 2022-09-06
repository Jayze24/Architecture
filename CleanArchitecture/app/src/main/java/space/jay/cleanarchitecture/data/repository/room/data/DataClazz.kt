package space.jay.cleanarchitecture.data.repository.room.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import space.jay.cleanarchitecture.domain.entity.Clazz

@Keep
@Entity(tableName = "clazz")
data class DataClazz(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    override var name: String,
    override var capacity: Int,
) : Clazz
