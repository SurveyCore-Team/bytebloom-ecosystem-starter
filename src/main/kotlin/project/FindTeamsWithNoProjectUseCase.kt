package domain.useCase.project

import domain.model.Projects
import domain.repository.ProjectRepository
import domain.useCase.BaseUseCase

class FindProjectAssignedToTeamUseCase(
    private val projectRepository: ProjectRepository
) : BaseUseCase<String, Projects?> {

    override fun invoke(teamId: String): Projects? {
        return projectRepository.getAllProjects()
            .find { it.teamId == teamId }
    }
}