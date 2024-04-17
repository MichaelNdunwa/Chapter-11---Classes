import java.util.*

fun main() {
    val john = Person(firstName = "Johnny", lastName = "Appleseed")
    println("full name: ${john.fullName}")

    /// Reference types
    var var1 = SimplePerson(name = "John")
    var var2 = var1

    /// Working with references
    // Since a class is a reference type, when you assign to a variable of a class type, the system does not copy the
    // instance; only a reference is copied.
    var homeOwner = john
    john.firstName = "John"

    println("john.firstName: ${john.firstName}")
    println("homeOwner.firstName: ${homeOwner.firstName}")  // > John (how?, because of Reference type)

    /// Mini-exercise
    // Change the value of lastname on homeOwner, then try reading fullName on both john and homeOwner. What do you observe?
    homeOwner.lastName = "Waugh"

    println("john.fullName: ${john.fullName}")
    println("homeOwner.fullName: ${homeOwner.fullName}")

    /// Object identity
    println("homeOwner === john = ${homeOwner === john}")

    val impostorJohn = Person(firstName = "John", lastName = "Appleseed")
    // NB: Even if john object and impostorJohn object contains the same arguments their reference address will be different.

    println("john === homeOwner: ${john === homeOwner}")
    println("john === impostorJohn: ${john === impostorJohn}")
    println("impostorJohn === homeOwner: ${impostorJohn === homeOwner}")

    // Assignment of existing variable changes the instances the variables reference.
    homeOwner = impostorJohn
    println("john === homeOwner: ${john === homeOwner}")

    homeOwner = john
    println("john === homeOwner: ${john === homeOwner}")


    // Create fake, imposter Johns.
    var imposters = (0..100).map {
        Person(firstName = "John", lastName = "Appleseed")
    }

    // Equality (==) is not effective when John cannot be identified by his name alone.
    imposters.map {
        it.firstName == "John" && it.lastName == "Appleseed"
    }.contains(true)

    // Let check how many imposters are there:
    val howManyImposters = imposters.map {
        it.firstName == "John" && it.lastName == "Appleseed"
    }.count { it == true }
    println("There are $howManyImposters imposters here.")

    // Check to ensure the real John is not found among the imposters.
    println("Is John an imposter: ${imposters.contains(john)}")

    // Now hide the "real" John somewhere among the imposters.
    val mutableImposters = mutableListOf<Person>()
    mutableImposters.addAll(imposters)
    println("Check again if John is an imposter: ${mutableImposters.contains(john)}")
    mutableImposters.add(Random().nextInt(5), john)

    // John can now be found among the imposters.
    println("Check for the last time if John is an imposter: ${mutableImposters.contains(john)}")


    // Since `Person` is a reference type, you can use === to grab the real John out of the list of imposters and modify the value.
    // The original `john` variable will print the new last name!
    val indexOfJohn = mutableImposters.indexOf(john)
    if (indexOfJohn != -1) {
        mutableImposters[indexOfJohn].lastName = "Bananapeel"
    }
    println(john.fullName)


    /// Mini-exercise
    //Test it by creating two arrays of five Person objects for group and using john as the
    //person. Put john in one of the arrays, but not in the other.
    val firstFivePersons = Array(5) {
        Person("Michael", "Ndunwa")
    }

    val secondFivePersons = arrayOf(
        john,
        Person("Anthony", "Ndunwa"),
        Person("Ekwueme", "Amos"),
        Person("Kingsley", "Obianlo"),
        Person("Christopher", "Anyichie")
    )


    println("Is John in the group of the first five person's?: ${john.memberOf(john, firstFivePersons)}")
    println("Is John in the group of the second five person's?: ${john.memberOf(john, secondFivePersons)}")
}


/// Creating classes
class Person(var firstName: String, var lastName: String) {
    val fullName
        get() = "$firstName $lastName"

    // Mini-exercise
    //Write a function memberOf(person: Person, group: List<Person>): Bool that
    //will return true if person can be found inside group, and false if it can not.

    //Test it by creating two arrays of five Person objects for group and using john as the
    //person. Put john in one of the arrays, but not in the other.
    fun memberOf(person: Person, group: Array<Person>): Boolean {
        return group.contains(person)
    }
}

/// Reference types
class SimplePerson(val name: String)