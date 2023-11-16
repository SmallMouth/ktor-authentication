package authentication.ktor.domain.repository

import authentication.ktor.data.model.User


interface UserRepository {
    fun findUser(email: String, password: String): User?
    fun addUser(user: User): User?
}