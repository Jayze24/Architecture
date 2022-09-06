package space.jay.cleanarchitecture.domain.entity

import space.jay.cleanarchitecture.data.repository.retrofit.data.DataLink

interface Topic {
    val description: String?
    val endsAt: Any?
    val featured: Boolean?
    val id: String?
    val links: DataLink?
    val onlySubmissionsAfter: Any?
    val publishedAt: String?
    val slug: String?
    val startsAt: String?
    val status: String?
    val title: String?
    val totalPhotos: Int?
    val updatedAt: String?
    val visibility: String?
}

interface Links {
    val html: String?
    val photos: String?
    val self: String?
}