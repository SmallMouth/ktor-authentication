package authentication.ktor.plugins

import authentication.ktor.domain.routers.auth.authRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authRoutes()
    }
}
