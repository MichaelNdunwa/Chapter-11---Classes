
fun main() {
//    CollegeStudent(firstName = "Michael", lastName = "Ndunwa", id = 111408).hashCode()

    val albert = CollegeStudent(
        firstName = "Albert",
        lastName = "Einstein",
        id = 1
    )

    val richard = CollegeStudent(
        firstName = "Richard",
        lastName = "Feynman",
        id = 2
    )
    val albertCopy = albert.copy()

    println("albert: $albert")
    println("richard: $richard")
    println("albertCopy: $albertCopy")

    // Equality Operator and Reference Operator
    println("albert == richard: ${albert == richard}")
    println("albert == albertCopy: ${albert == albertCopy}")
    println("albert === albertCopy: ${albert === albertCopy}")


    // Checking out the data class in action. Add the following:
    val marie = CollegeStudentData("Marie", "Curie", id = 1)
    val emmy = CollegeStudentData("Emmy", "Noether", id = 2)
    val marieCopy = marie.copy()

    println("marie: $marie")
    println("emmy: $emmy")

    println("marie == emmy: ${marie == emmy}")
    println("marie == marieCopy: ${marie == marieCopy}")
    println("marie === marieCopy: ${marie === marieCopy}")

    /// Destructuring declarations
    val (firstName, lastName, id) = marie // destructing declaration in data class.

    println("firstName: $firstName")
    println("lastName: $lastName")
    println("id: $id")

    val numbers = (1..4).toList()
    val (one, two, three, four) = numbers // destructuring declaration in collection.
    println(one)
    println(two)
    println(three)
    println(four)
}


/***
 * Suppose you want to define a Student class and have added functionality, such as the ability to compare whether two
 * students are equal in value or the ability to easily print the student data. You might define the class as follows:
 */
class CollegeStudent(
    var firstName: String,
    var lastName: String,
    var id: Int
) {

    override fun hashCode(): Int {
        val prime = 31
        var result = 1

        result = prime * result + firstName.hashCode()
//        println(result)
        result = prime * result + id
//        println(result)
        result = prime * result + lastName.hashCode()
//        println(result)

        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other == null)
            return false

        if (javaClass != other.javaClass)
            return false

        val obj = other as CollegeStudent?

        if (firstName != obj?.firstName)
            return false

        if (id != obj.id)
            return false

        if (lastName != obj.lastName)
            return false

        return true
    }

    override fun toString(): String {
        return "Student (firstNames=$firstName, lastName=$lastName, id=$id)"
    }

    fun copy(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        id: Int = this.id
    ): CollegeStudent {
        return CollegeStudent(firstName, lastName, id)
    }

}

data class CollegeStudentData(
    var firstName: String,
    var lastName: String,
    var id: Int
)