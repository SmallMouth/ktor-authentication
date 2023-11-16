package authentication.ktor.di

import authentication.ktor.data.model.User
import authentication.ktor.data.repository.UserRepositoryImpl
import authentication.ktor.domain.repository.UserRepository
import authentication.ktor.domain.routers.auth.AuthService
import authentication.ktor.domain.routers.auth.AuthServiceImpl
import com.mongodb.client.MongoDatabase
import org.koin.dsl.module
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<AuthService> { AuthServiceImpl(get()) }
}

val dbModule = module {
    single {
        val client = KMongo.createClient()
        client.getDatabase("account-test")
    }

    single {
        get<MongoDatabase>().getCollection<User>("User")
    }
    single {
        UserRepositoryImpl(get())
    }
}