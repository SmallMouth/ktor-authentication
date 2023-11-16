package authentication.ktor.domain.routers.auth

import authentication.ktor.domain.model.LoginRequest
import authentication.ktor.domain.model.RegisterRequest
import authentication.ktor.domain.model.dto.UserDto

interface AuthService {
    fun login(loginRequest: LoginRequest): UserDto?
    fun register(registerRequest: RegisterRequest): UserDto?
    fun logout()
}