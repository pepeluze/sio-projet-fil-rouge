package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Account
import org.springframework.data.repository.CrudRepository

/**
 * A data repository to expose account-related entities.
 */
interface AccountsRepository  : CrudRepository<Account, Long> {

    /**
     * Find an account with a given name.
     * The matching is case-insensitive.
     * @param username The name of the user.
     * @return The [Account] entity, or null if it does not exist.
     */
    fun findByUsername(username: String): Account?

    // TODO: Add other movie-related methods here

}
