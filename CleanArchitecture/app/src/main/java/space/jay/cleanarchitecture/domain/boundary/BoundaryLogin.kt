package space.jay.cleanarchitecture.domain.boundary

import space.jay.cleanarchitecture.domain.entity.user.User

interface BoundaryLogin {

    suspend fun getUser(email: String, password: String): User?

}