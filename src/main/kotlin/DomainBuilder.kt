import com.bytebloom.model.raw.MenteeRaw
import com.bytebloom.model.raw.PerformanceRaw
import com.bytebloom.model.raw.TeamRaw
import domain.PerformanceSubmission
import domain.Mentee
import domain.Team

class DomainBuilder {

    private var menteesDomainList = mutableListOf<Mentee>()
    private var teamsDomainList= mutableListOf<Team>()
    private var SubmissionsDomainList= mutableListOf<PerformanceSubmission>()
    
    private var SubmissionseMap = mutableMapOf<String,List<PerformanceSubmission>>()
    private var menteesMap= mutableMapOf<String,Mentee>()
    private var teamsMap= mutableMapOf<String,Team>()

    fun buildDomain(PerformanceRawList: MutableList<PerformanceRaw>, teamRawList: MutableList<TeamRaw>,
    menteeRawList: MutableList<MenteeRaw>):MutableList<Team>{
            createDomainLists(PerformanceRawList, teamRawList, menteeRawList)
            convertListsToMap()
            createLinkes()
            return teamsDomainList
    }
    private fun createDomainLists(PerformanceRawList: MutableList<PerformanceRaw>, teamRawList: MutableList<TeamRaw>,
    menteeRawList: MutableList<MenteeRaw>){
            createsubmissinDomainLists(PerformanceRawList)
            createTeamDomainLists(teamRawList)
            createMenteeDomainLists(menteeRawList)
    }
    private fun convertListsToMap(){
        createperformanceMap()
        createMenteeDomainMap()
        createTeamDomainMap()
    }
    private fun createLinkes(){
         for(Raw in menteesDomainList) {
             val mentee = menteesMap[Raw.Id]
             val team = teamsMap[Raw.teamId]
             if (mentee != null) {
                 if (team != null) {
                     team.mentees.add(Raw.Id)
                 }
                 val submissions = SubmissionseMap[Raw.Id]
                 if (submissions != null) {
                     submissions.toMutableList()
                     mentee.submissions = submissions.map {
                         it.submissionId
                     }.toMutableList()
                 }
             }
         }
    }
    private fun createMenteeDomainLists(menteeRawList: MutableList<MenteeRaw>) {
        this.menteesDomainList = menteeRawList.map { menteeRaw ->
            Mentee(
                Id = menteeRaw.Id,
                name = menteeRaw.name,
                teamId = menteeRaw.teamId,
                submissions = mutableListOf<String>()
            )
        }.toMutableList()
    }
    private fun createTeamDomainLists(teamRawList: MutableList<TeamRaw>) {
        this.teamsDomainList = teamRawList.map { teamRaw ->
            Team(
                Id = teamRaw.Id,
                name = teamRaw.name,
                mentorLead = teamRaw.mentorLead,
                mentees = mutableListOf<String>()
            )
        }.toMutableList()
    }
    private fun createsubmissinDomainLists(PerformanceRawList: MutableList<PerformanceRaw>){
        this.SubmissionsDomainList = PerformanceRawList.map { performanceSubmissionRaw ->
            PerformanceSubmission(
                menteeId = performanceSubmissionRaw.menteeId,
                submissionId = performanceSubmissionRaw.submissionId,
                submissionType = performanceSubmissionRaw.submissionType,
                score = performanceSubmissionRaw.score
            )
        }.toMutableList()
    }
    private fun createMenteeDomainMap(){
        this.menteesMap = this.menteesDomainList.associateBy { it.Id }.toMutableMap()
    }
    private fun createTeamDomainMap(){
        this.teamsMap = this.teamsDomainList.associateBy { it.Id }.toMutableMap()
    }
    private fun createperformanceMap(){
        this.SubmissionseMap = this.SubmissionsDomainList.groupBy { it.menteeId }.toMutableMap()
    }
    fun getMenteeList(): MutableList<Mentee> {
        return this.menteesDomainList
    }
}

