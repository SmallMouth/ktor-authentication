package authentication.ktor.plugins

import authentication.ktor.di.appModule
import authentication.ktor.di.dbModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.koin

fun Application.configureKoin() {
    koin {
        modules(appModule, dbModule)
    }
}
