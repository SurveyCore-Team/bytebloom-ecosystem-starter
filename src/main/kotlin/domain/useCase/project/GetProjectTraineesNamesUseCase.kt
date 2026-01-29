package domain.usecase.project

import domain.repository.ProjectRepository
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class GetProjectTraineesNamesUseCase(
    private val projectRepository: ProjectRepository,
    private val teamRepository: TeamRepository
) : BaseUseCase<String, List<String>> {

    override fun invoke(projectId: String): List<String> {
        val project = projectRepository.getAllProjects()
            .find { it.id == projectId }
        return project?.let { pro ->
            teamRepository.getAllTeams()
                .find { it.id == pro.teamId }
                ?.members
                ?.map { it.name }
        } ?: emptyList()
    }
}