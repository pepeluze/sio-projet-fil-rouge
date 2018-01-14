package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Movie
import fr.centralesupelec.sio.model.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository  : CrudRepository<Person, Long>