package space.jay.cleanarchitecture.domain.entity.user

interface User {
    val id: Long
    val email: String
    val password: String
    var name: String
    var type: TypeUser

    fun isProfessor() = type == TypeUser.PROFESSOR
    fun isStudent() = type == TypeUser.STUDENT
}