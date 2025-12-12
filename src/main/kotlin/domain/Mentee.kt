package domain

data class Mentee (
    val Id: String,
    val name: String,
    val teamId: String,
    var submissions:MutableList<String>
)

