package challenges

/**
 * Challenge 1: Movie lists
 *
 * Imagine you’re writing a movie-viewing application in Kotlin. Users can create lists
 * of movies and share those lists with other users.
 *
 * Create a User class and a MovieList class that maintains lists for users.
 *
 * • MovieList: Contains a name and a mutable list of movie titles. The name and
 *      titles can all be represented by Strings. A print method will print all the movies in the movie list.
 *
 * • User: Has a method addList() which adds the given MovieList to a mutable map
 *      of MovieList objects (using the name as a key), and list(name: String): MovieList? which will return
 *      the MovieList for the provided name.
 *
 * • Create jane and john users and have them create and share lists. Have both jane
 *       and john modify the same list and call print from both users. Are all the changes reflected?
 */

fun main() {
    val actionList = MovieList("Action", mutableListOf("Badland Hunters", "The Beekeeper", "Civil War"))
    val romanceList = MovieList("Romance", mutableListOf("Upgraded", "Players", "Love, Divided", "How to Date Billy Walsh"))

    // Give John and Jane an "Action" list
    val jane = User()
    val john = User()

    jane.addList(actionList)
    john.addList(actionList)

    // Add Jane's favorites
    jane.lists["Action"]?.movies?.add("Rambo")
    jane.lists["Action"]?.movies?.add("Terminator")
}

class MovieList(val name: String, val movies: MutableList<String> = mutableListOf()) {
    fun print() {
        println("Movie List: $name")
        movies.forEach {
            println(it)
        }
    }
}

class User(val lists: MutableMap<String, MovieList> = mutableMapOf<String, MovieList>()) {

    fun addList(list: MovieList) {
        lists[list.name] = list
    }

    fun list(name: String): MovieList? = lists[name]

}