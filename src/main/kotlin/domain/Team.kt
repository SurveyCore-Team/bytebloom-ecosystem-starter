package domain

data class Team (
    val Id: String,
    val name: String,
    val mentorLead: String,
    val mentees: MutableList<String>
)
