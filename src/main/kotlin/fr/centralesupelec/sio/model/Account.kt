package fr.centralesupelec.sio.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * An entity class to represent a user account.
 */
@Entity
data class Account(
        val username: String,
        val passwordHash: String? = null,
        @Id @GeneratedValue
        val id: Long = -1
)
