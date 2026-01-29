package domain.usecase.project

import domain.repository.ProjectRepository
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class GetTraineesWithNoProjectsUseCase(
    private val teamRepository: TeamRepository,
    private val projectRepository: ProjectRepository
) : BaseUseCase<Unit, List<String>> {

    override fun invoke(input: Unit): List<String> {
        val assignedTeamIds = projectRepository.getAllProjects()
            .map { it.teamId }
            .toSet()

        return teamRepository.getAllTeams()
            .filter { it.id !in assignedTeamIds }
            .flatMap { it.members }
            .map { it.name }
            .distinct()
    }
}