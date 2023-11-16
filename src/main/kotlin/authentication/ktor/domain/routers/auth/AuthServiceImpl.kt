package authentication.ktor.domain.routers.auth

import authentication.ktor.data.model.User
import authentication.ktor.domain.model.LoginRequest
import authentication.ktor.domain.model.RegisterRequest
import authentication.ktor.domain.model.dto.UserDto
import authentication.ktor.domain.repository.UserRepository
import io.ktor.server.plugins.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*

class AuthServiceImpl(private val userRepository: UserRepository) : AuthService {

    override fun login(loginRequest: LoginRequest): UserDto {
        userRepository.findUser(loginRequest.email, loginRequest.password)?.let {
            return UserDto(username = it.username, email = it.email, token = "token")
        } ?: throw NotFoundException("User not found")
    }

    override fun register(registerRequest: RegisterRequest): UserDto {
        //registerRequest to User Model
        val user = User(
            id = UUID.randomUUID().toString(),
            email = registerRequest.email,
            username = registerRequest.username,
            passwordHash = BCrypt.hashpw(registerRequest.password, BCrypt.gensalt()),
            status = true
        )
        userRepository.addUser(user)?.let {
            return UserDto(username = it.username, email = it.email, token = "token")
        } ?: throw Exception("register fail")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

}