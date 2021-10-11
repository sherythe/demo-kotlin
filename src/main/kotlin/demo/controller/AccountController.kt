package demo.controller

import demo.model.Account
import demo.repository.AccountRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountRepository: AccountRepository) {

    @PostMapping()
    fun createNewAccount(@RequestBody account: Account): Account =
            accountRepository.save(account)

}