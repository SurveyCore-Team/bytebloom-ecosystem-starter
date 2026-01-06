package datasource.model

data class TeamRaw(
    val id :String,
    val name: String,
    val mentorLead: String,
    var members: List<MenteeRaw>
)