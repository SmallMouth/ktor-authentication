package authentication.ktor.data.repository

import authentication.ktor.data.model.User
import authentication.ktor.domain.repository.UserRepository
import com.mongodb.MongoWriteException
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.IndexOptions
import org.litote.kmongo.ensureIndex
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.mindrot.jbcrypt.BCrypt

class UserRepositoryImpl(private val mongoCollection: MongoCollection<User>) : UserRepository {
    init {
        mongoCollection.ensureIndex("{email: 1}", IndexOptions().unique(true))
        mongoCollection.ensureIndex("{username: 1}", IndexOptions().unique(true))
    }

    override fun findUser(email: String, password: String): User? {
        val user = mongoCollection.findOne(User::email eq email)
        val checkPW = BCrypt.checkpw(password, user?.passwordHash)
        return if (checkPW) user else null
    }

    override fun addUser(user: User): User? {
        try {
            mongoCollection.insertOne(user).let {
                return mongoCollection.findOne(User::email eq user.email)
            }
        } catch (e: MongoWriteException) {
            if (e.error.code == 11000) {
                // 這裡處理重複鍵的情況
                println("發生了重複鍵錯誤: ${e.message}")
                // 可以拋出自定義的異常或進行其他錯誤處理
            } else {
                // 處理其他類型的寫入錯誤
                println("發生了其他寫入錯誤: ${e.message}")
            }
            return null
        }

    }

}