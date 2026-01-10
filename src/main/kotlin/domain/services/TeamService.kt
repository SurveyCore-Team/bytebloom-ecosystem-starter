package domain.services

import domain.model.Team
import domain.model.Projects
import domain.repository.contracts.TeamRepository
import domain.repository.contracts.ProjectRepository

class TeamService(
    private val teamRepository: TeamRepository,
    private val projectRepository: ProjectRepository
) {

    fun findLeadMentorForMentee(menteeId: String): String? {
        val allTeams = teamRepository.getAllTeams()
        val team = allTeams.find { team ->
            team.members.any { it.id == menteeId }
        }
        return team?.mentorLead
    }

    fun getOverallPerformanceAverageForTeam(teamId: String): Double {
        val team = teamRepository.getAllTeams()
            .find { it.id == teamId }
            ?: return 0.0
        val allScoring = team.members.flatMap { it.submissions }.map { it.score.toDouble() }
        return allScoring.average()

    }

    fun findTeamsWithNoProject(): List<Team> {
        val allTeams: List<Team> = teamRepository.getAllTeams()
        val allProjects: List<Projects> = projectRepository.getAllProjects()

        val assignedTeamIds: Set<String> = allProjects.map { it.teamId }.toSet()

        return allTeams.filter { team: Team -> team.id !in assignedTeamIds }
    }

    fun findProjectAssignedToTeam(teamId: String): Projects? {
        val allProjects: List<Projects> = projectRepository.getAllProjects()
        return allProjects.find { it.teamId == teamId }
    }
}