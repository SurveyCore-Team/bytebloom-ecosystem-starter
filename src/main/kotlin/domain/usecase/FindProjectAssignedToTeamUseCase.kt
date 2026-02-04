package domain.usecase

import domain.model.Project
import domain.repository.ProjectRepository

class FindProjectAssignedToTeamUseCase(
    private val projectRepository: ProjectRepository
) : BaseUseCase<String, Project?> {

    override fun invoke(teamId: String): Project? {
        return projectRepository.getAllProjects()
            .find { it.teamId == teamId }
    }
}