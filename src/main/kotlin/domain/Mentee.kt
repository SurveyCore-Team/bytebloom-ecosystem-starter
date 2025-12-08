package domain

data class Mentee (
    val menteeId: String,
    val name: String,
    val teamId: String,
    val submissions: List<PerformanceSubmission>
) {
    fun attachMenteeToTeam (menteeId: String, teamId: String) {
        val currentMenteeId = submissions.find { teamId == it.menteeId }


    }
  }

