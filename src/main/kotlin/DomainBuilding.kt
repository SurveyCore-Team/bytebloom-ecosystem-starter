import com.bytebloom.model.raw.MenteeRaw
import com.bytebloom.model.raw.PerformanceRaw
import com.bytebloom.model.raw.TeamRaw
import domain.PerformanceSubmission
import domain.Mentee
import domain.Team

class DomainBuilder {

    private var mentee:MutableList<Mentee>? = null
    private var teams: MutableList<Team>? = null
    private var performanceSubmission: MutableList<PerformanceSubmission>? = null
    
    private var performanceMap: MutableMap<String,List<PerformanceSubmission>>?=null
    private var menteeMap:MutableMap<String,Mentee>?=null
    private var teamMap:MutableMap<String,Team>?=null
    

    fun buildDomain(PerformanceRawList: MutableList<PerformanceRaw>, teamRawList: MutableList<TeamRaw>, menteeRawList: MutableList<MenteeRaw>
    ):MutableList<Team>?{
        createDomainLists(PerformanceRawList, teamRawList, menteeRawList)
        convertListsToMap()
        createLinkes()
        return teams
    }

    private fun createDomainLists(PerformanceRawList: MutableList<PerformanceRaw>, teamRawList: MutableList<TeamRaw>, menteeRawList: MutableList<MenteeRaw>){
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
         for(Raw in mentee!!){
             val mentee=menteeMap!![Raw.menteeId]
             val team=teamMap!![Raw.teamId]
             if(mentee != null){
                 if(team != null){
                  team.mentees?.add(Raw.menteeId)
                 }
                 val performance=performanceMap!![Raw.menteeId]
                 if(performance != null){
                     mentee.submissions=performance.map{it ->
                         it.submissionId
                     }.toMutableList()
                 }
             }
         }


    }

    private fun createMenteeDomainLists(menteeRawList: MutableList<MenteeRaw>) {
        this.mentee = menteeRawList.map { menteeRaw ->
            Mentee(
                menteeId = menteeRaw.Id,
                name = menteeRaw.name,
                teamId = menteeRaw.teamId,
                submissions = null
            )

        }.toMutableList()
        
    }
    private fun createTeamDomainLists(teamRawList: MutableList<TeamRaw>) {
        this.teams = teamRawList.map { teamRaw ->
            Team(
                teamId = teamRaw.menteeId,
                submissionId = teamRaw.submissionId,
                mentorLead = teamRaw.mentorLead,
                mentees = null
            )

        }.toMutableList()
        
    }
    private fun createsubmissinDomainLists(PerformanceRawList: MutableList<PerformanceRaw>){
        this.performanceSubmission = PerformanceRawList.map { performanceSubmissionRaw ->
            PerformanceSubmission(
                menteeId = performanceSubmissionRaw.menteeId,
                submissionId = performanceSubmissionRaw.submissionId,
                submissionType = performanceSubmissionRaw.submissionType,
                score = performanceSubmissionRaw.score
            )

        }.toMutableList()
    }

    private fun createMenteeDomainMap(){
        this.menteeMap = this.mentee?.associateBy { it.menteeId }?.toMutableMap()
    }
    private fun createTeamDomainMap(){
        this.teamMap = this.teams?.associateBy { it.teamId }?.toMutableMap()
    }
    private fun createperformanceMap(){
        this.performanceMap = this.performanceSubmission?.groupBy { it.menteeId }?.toMutableMap()
    }
}

