package challenges

/***
 * Challenge 2: T-Shirt store — data classes
 * Your challenge here is to build a set of objects to support a T-shirt store. Decide if
 * each object should be a class or a data class, and go ahead and implement them all.
 * • TShirt: Represents a shirt style you can buy. Each TShirt has a size, color, price,
 * and an optional image on the front.
 * • User: A registered user of the t-shirt store app. A user has a name, email, and a
 * ShoppingCart (see below).
 * • Address: Represents a shipping address, containing the name, street, city, and zip
 * code.
 * • ShoppingCart: Holds a current order, which is composed of a list of TShirts that
 * the User wants to buy, as well as a method to calculate the total cost. Additionally,
 * there is an Address that represents where the order will be shipped.
 */
import java.text.NumberFormat
import java.util.Locale

fun main() {
    // Creating T-Shirts:
    val classicTee = TShirt(size = "XL", color = "Black", price = 200.00)
    val stripedTee = TShirt(size = "L", color = "Blue", price = 600.00)
    val graphicTee = TShirt(size = "M", color = "Green", price = 400.00)
    val vNeckTee = TShirt(size = "S", color = "White", price = 800.00)
    val henleyTee = TShirt(size = "L", color = "Gray", price = 500.00)

    val address = Address(name = "Oceanic Lodge", street = "Umuarigha", city = "Umuahia", zipCode = 11044)

    val cart = ShoppingCart(mutableListOf(classicTee, stripedTee, graphicTee, vNeckTee, henleyTee), address)

    val customer = UserAccount(name = "Michael", email = "ndunwa240@gmail.com", cart = cart)
    val totalCost = customer.cart.cost().toNaira()

    println("${customer.name} you're to pay $totalCost")

    println("Everything will cost: ${cart.cost().toNaira()}")
}

class TShirt(
    val size: String,
    val color: String,
    val price: Double,
    val image: String? = null
)

data class UserAccount(
    val name: String,
    val email: String,
    val cart: ShoppingCart
)

data class Address(
    val name: String,
    val street: String,
    val city: String,
    val zipCode: Int
)

class ShoppingCart( val tShirts: MutableList<TShirt>, val address: Address ) {
    val shippingAddress = "${address.name}, ${address.street}, ${address.city}, ${address.zipCode}."

    fun addTShirt(tShirt: TShirt) {
        tShirts.add(tShirt)
    }

    fun cost(): Double {
        var totalCost = 0.0
        tShirts.forEach {
            totalCost += it.price
        }
        return totalCost
    }

}


fun Double.toNaira(): String? {
//    val naira = NumberFormat.getCurrencyInstance(Locale("en", "NG")) // This method have be deprecated.
    val naira = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-NG"))
    return naira.format(this)
}