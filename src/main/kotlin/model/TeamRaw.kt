package model

data class TeamRaw(
    val id :String,
    val name: String,
    val mentor: String,
    var members: List<MenteeRaw>
)