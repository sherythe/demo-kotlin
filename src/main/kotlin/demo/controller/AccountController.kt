package demo.controller

import demo.model.Account
import demo.repository.AccountRepository
import org.springframework.http.HttpStatus
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

    @PutMapping("/edit/{id}")
    fun updateAccountById(@PathVariable(value = "id") accountId: Long, @RequestBody newAccountInfo: Account): ResponseEntity<Account> {
        return accountRepository.findById(accountId).map { existingAccount ->
            val updatedAccount: Account = existingAccount.copy(
                name = newAccountInfo.name,
                address = newAccountInfo.address,
                occupation = newAccountInfo.occupation
            )
            ResponseEntity(accountRepository.save(updatedAccount), HttpStatus.OK)
        }.orElse(ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR))
    }

}