package demo.controller

import demo.model.User
import demo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping()
    fun createNewUser(@RequestBody user: User): User =
        userRepository.save(user)

    @GetMapping("/view/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<User> {
        return userRepository.findById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/edit/{id}")
    fun updateUserById(@PathVariable(value = "id") userId: Long, @RequestBody newUserInfo: User): ResponseEntity<User> {
        return userRepository.findById(userId).map { existingAccount ->
            val updatedAccount: User = existingAccount.copy(
                name = newUserInfo.name,
                email = newUserInfo.email,
                address = newUserInfo.address,
                occupation = newUserInfo.occupation
            )
            ResponseEntity(userRepository.save(updatedAccount), HttpStatus.OK)
        }.orElse(ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

}