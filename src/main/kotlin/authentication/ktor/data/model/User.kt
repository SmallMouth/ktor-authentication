package authentication.ktor.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User(

	@JsonProperty("id")
	val id: String,

	@JsonProperty("email")
	val email: String,

	@JsonProperty("passwordHash")
	val passwordHash: String,

	@JsonProperty("username")
	val username: String,

	@JsonProperty("status")
	val status: Boolean
)
