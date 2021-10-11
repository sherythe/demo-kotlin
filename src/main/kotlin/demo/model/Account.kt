package demo.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id : Long,
        var name : String,
        var address : String,
        var occupation : String,
        var accountLimit : BigDecimal,
        var usedLimit : BigDecimal,
        var isActive : Boolean = true
)
