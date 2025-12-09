package domain

data class Team (
    val teamId: String,
    val submissionId: String,
    val mentorLead: String,
    val mentees: MutableList<String> ?
)
