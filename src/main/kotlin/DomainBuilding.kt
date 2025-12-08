import com.bytebloom.model.raw.MenteeRaw
import com.bytebloom.model.raw.PerformanceRaw
import com.bytebloom.model.raw.TeamRaw
import domain.PerformanceSubmission
import domain.Mentee
import domain.Team

class DomainBuilding {
    fun bildDomain(
        PerformanceRawList : List<PerformanceRaw>,
        teamRawList : List<TeamRaw>,
        menteeRawList : List<MenteeRaw>){

        val performanceSubmission = PerformanceRawList.map {
            performanceSubmissionRaw ->
            PerformanceSubmission(
                menteeId = performanceSubmissionRaw.menteeId,
                submissionId = performanceSubmissionRaw.submissionId,
                submissionType = performanceSubmissionRaw.submissionType,
                score = performanceSubmissionRaw.score
            )

        }

        val teams = teamRawList.map {
            teamRaw ->
            Team(
                menteeId = teamRaw.menteeId,
                submissionId = teamRaw.submissionId,
                mentorLead = teamRaw.mentorLead,
                mentees = null
            )

        }

        val mentee = menteeRawList.map { menteeRaw ->
            Mentee(
                menteeId = menteeRaw.Id,
                name = menteeRaw.name,
                teamId = menteeRaw.teamId,
                submissions = null
            )

        }



    }
}