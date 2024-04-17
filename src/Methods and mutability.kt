import kotlin.math.roundToInt

/// Methods and mutability

fun main() {
    val jane = Student(firstName = "Jane", lastName = "Appleseed")
    val history = Grade(letter = "B", points = 9.0, credits = 3.0)
    var math = Grade(letter = "A", points = 16.0, credits = 4.0)

    jane.recordGrade(history)
    jane.recordGrade(math)


    /// Mutability and constants
    println(jane.gpa)

    /// Understanding state and side effects
    println("Jane's credit is: ${jane.credits}")

    // The teacher made a mistake: math has 5 credits
    jane.credits -= math.credits // I removed the initial math credit, so that the finial credit will be 8.0 instead of 12!

    math = Grade(letter = "A", points = 20.0, credits = 5.0)
    jane.recordGrade(math)

    println("The teacher made a mistake(which have been corrected), so Jane's credit is now: ${jane.credits}")

}

class Grade(
    val letter: String,
    val points: Double,
    val credits: Double
)

class Student(
    val firstName: String,
    val lastName: String,
    val grades: MutableList<Grade> = mutableListOf(),
    var credits: Double = 0.0
) {

    /***
     * Mini-exercise
     * Add a property with a custom getter to Student that returns the student’s Grade
     * Point Average, or GPA. A GPA is defined as the number of points earned divided by
     * the number of credits taken. For the example above, Jane earned (9 + 16 = 25) points
     * while taking (3 + 4 = 7) credits, making her GPA (25 / 7 = 3.57).
     *
     * Note: Points in most American universities range from 4 per credit for an A,
     * down to 1 point for a D (with an F being 0 points). For this exercise, you may of
     * course use any scale that you want!
     */
    val gpa: Double
        get() {
            var sumOfPoints = 0.0
            grades.forEach {
                sumOfPoints += it.points
            }
//            return (sumOfPoints / credits)
            return "%.2f".format(sumOfPoints / credits).toDouble() // rounded it up to 2 significant points.
        }


    fun recordGrade(grade: Grade) {
        grades.add(grade)
        credits += grade.credits
    }
}