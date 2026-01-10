package domain.services

import domain.repository.contracts.ProjectRepository
import domain.repository.contracts.TeamRepository
import domain.model.Projects
import domain.model.Team

class TeamService(
    private val teamRepository: TeamRepository,
    private val projectRepository: ProjectRepository
) {
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