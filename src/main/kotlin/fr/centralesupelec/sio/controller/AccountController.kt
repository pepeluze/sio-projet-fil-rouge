package fr.centralesupelec.sio.controller

import fr.centralesupelec.sio.data.AccountsRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@EnableOAuth2Client
@RestController
@RequestMapping("/accounts")
@Api(value="movies", description="Operations pertaining to accounts")
class AccountController (private val accountsRepository: AccountsRepository) {

    @ApiOperation(value = "View the list of all accounts", produces = "application/json")
    @ApiResponses(value = [(ApiResponse(code = 200, message = "Successfully retrieved list")),
        (ApiResponse(code = 401, message = "You are not authorized to view the resource")),
        (ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")),
        (ApiResponse(code = 404, message = "The resource you were trying to reach is not found"))])
    @GetMapping("/list")
    fun list() = accountsRepository.findAll()

    @ApiOperation(value = "View an account by the user name", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/find/{name}")
    fun find(@PathVariable name: String) = accountsRepository.findByUsername(name)

    @ApiOperation(value = "View an account by his ID", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/show/{id}")
    fun show(@PathVariable id: Long) = accountsRepository.findOne(id)
}