package domain

data class Team (
    val menteeId: String,
    val submissionId: String,
    val mentorLead: String,
    val mentees: List<Mentee> ?
)
