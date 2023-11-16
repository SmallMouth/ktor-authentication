package authentication.ktor.domain.routers.auth

import authentication.ktor.domain.model.LoginRequest
import authentication.ktor.domain.model.RegisterRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRoutes() {

    val authService by inject<AuthService>()

    route("/user") {
        post("/login") {
            val loginRequest = call.receive<LoginRequest>()
            val userDto = authService.login(loginRequest)
            if (userDto != null) {
                call.respond(userDto) // 使用 Jackson 自動序列化為 JSON
            } else {
                call.respond("登入失敗")
            }
        }
        post("/register") {
            val registerRequest = call.receive<RegisterRequest>()
            val userDto = authService.register(registerRequest)
            if (userDto != null) {
                call.respond(userDto) // 使用 Jackson 自動序列化為 JSON
            } else {
                call.respond("註冊失敗")
            }
        }
        post("/logout") {

        }
    }
}