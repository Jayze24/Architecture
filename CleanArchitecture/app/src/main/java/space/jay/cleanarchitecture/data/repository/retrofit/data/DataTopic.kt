package space.jay.cleanarchitecture.data.repository.retrofit.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import space.jay.cleanarchitecture.domain.entity.Links
import space.jay.cleanarchitecture.domain.entity.Topic

@Keep
data class DataTopic(
    @SerializedName("description")
    override val description: String?,
    @SerializedName("ends_at")
    override val endsAt: Any?,
    @SerializedName("featured")
    override val featured: Boolean?,
    @SerializedName("id")
    override val id: String?,
    @SerializedName("links")
    override val links: DataLink?,
    @SerializedName("only_submissions_after")
    override val onlySubmissionsAfter: Any?,
    @SerializedName("published_at")
    override val publishedAt: String?,
    @SerializedName("slug")
    override val slug: String?,
    @SerializedName("starts_at")
    override val startsAt: String?,
    @SerializedName("status")
    override val status: String?,
    @SerializedName("title")
    override val title: String?,
    @SerializedName("total_photos")
    override val totalPhotos: Int?,
    @SerializedName("updated_at")
    override val updatedAt: String?,
    @SerializedName("visibility")
    override val visibility: String?,
) : Topic

@Keep
data class DataLink(
    @SerializedName("html")
    override val html: String?,
    @SerializedName("photos")
    override val photos: String?,
    @SerializedName("self")
    override val self: String?,
) : Links