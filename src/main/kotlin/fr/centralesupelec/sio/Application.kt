package fr.centralesupelec.sio

import fr.centralesupelec.sio.data.AccountsRepository
import fr.centralesupelec.sio.data.MoviesRepository
import fr.centralesupelec.sio.data.PersonRepository
import fr.centralesupelec.sio.model.Account
import fr.centralesupelec.sio.model.Movie
import fr.centralesupelec.sio.model.MovieGenre
import fr.centralesupelec.sio.model.Person
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableOAuth2Sso
class Application {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(moviesRepository: MoviesRepository, accountsRepository: AccountsRepository, personRepository: PersonRepository) = CommandLineRunner {
        var directors = setOf(personRepository.save(Person("Peter", "Jackson")))
        var actors = setOf(personRepository.save(Person("Viggo", "Mortensen")))
        actors += personRepository.save(Person("Orlando", "Bloom"))
        actors += personRepository.save(Person("Ian", "McKellen"))
        actors += personRepository.save(Person("Billy", "Boyd"))

        moviesRepository.save(Movie("Lord of the Rings: The Return of the King", MovieGenre.FANTASY, directors, actors))

        directors = setOf(personRepository.save(Person("Rian", "Johnson")))
        actors = setOf(personRepository.save(Person("Daisy", "Ridley")))
        actors += personRepository.save(Person("Mark", "Hamill"))
        actors += personRepository.save(Person("Carrie", "Fisher"))
        actors += personRepository.save(Person("John", "Boyega"))
        actors += personRepository.save(Person("Adam", "Driver"))

        moviesRepository.save(Movie("Star Wars VIII: The Last Jedi", MovieGenre.SCIENCE_FICTION, directors, actors))

        directors = setOf(personRepository.save(Person("Matthew", "Vaughn")))
        actors = setOf(personRepository.save(Person("Taron", "Egerton")))
        actors += personRepository.save(Person("Channing", "Tatum"))
        actors += personRepository.save(Person("Colin", "Firth"))
        actors += personRepository.save(Person("Julianne", "Moore"))
        actors += personRepository.save(Person("Halle", "Berry"))

        moviesRepository.save(Movie("Kingsman 2: The Golden Circle", MovieGenre.ACTION, directors, actors))

        accountsRepository.save(Account("admin@ecp.sio.fr", "password"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}