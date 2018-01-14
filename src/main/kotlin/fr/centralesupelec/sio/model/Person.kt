package fr.centralesupelec.sio.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * An entity class to represent a user account.
 */
@Entity
data class Person(
        val firstName: String,
        val lastName: String,
        @Id @GeneratedValue
        val id: Long = -1
)