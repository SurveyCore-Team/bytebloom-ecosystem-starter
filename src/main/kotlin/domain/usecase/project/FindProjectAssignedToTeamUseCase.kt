package domain.usecase.project

import domain.model.Project
import domain.repository.ProjectRepository
import domain.usecase.BaseUseCase

class FindProjectAssignedToTeamUseCase(
    private val projectRepository: ProjectRepository
) : BaseUseCase<String, Project?> {

    override fun invoke(teamId: String): Project? {
        return projectRepository.getAllProjects()
            .find { it.teamId == teamId }
    }
}