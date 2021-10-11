package demo.controller

import demo.model.Account
import demo.repository.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountRepository: AccountRepository) {

    @PostMapping()
    fun createNewAccount(@RequestBody account: Account): Account =
            accountRepository.save(account)

    @GetMapping("/view/{id}")
    fun getAccountById(@PathVariable("id") accountId: Long): ResponseEntity<Account> {
        return accountRepository.findById(accountId).map { account ->
            ResponseEntity.ok(account)
        }.orElse(ResponseEntity.notFound().build())
    }

}