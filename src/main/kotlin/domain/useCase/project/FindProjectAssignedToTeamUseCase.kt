package domain.usecase.project

import domain.model.Team
import domain.repository.ProjectRepository
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class FindTeamsWithNoProjectUseCase(
    private val teamRepository: TeamRepository,
    private val projectRepository: ProjectRepository
) : BaseUseCase<Unit, List<Team>> {

    override fun invoke(input: Unit): List<Team> {
        val assignedTeamIds = projectRepository.getAllProjects()
            .map { it.teamId }
            .toSet()

        return teamRepository.getAllTeams()
            .filter { it.id !in assignedTeamIds }
    }
}