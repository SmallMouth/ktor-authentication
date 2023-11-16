package authentication.ktor.domain.model.dto

data class UserDto(
    val username: String,
    val email: String,
    val token: String
)
