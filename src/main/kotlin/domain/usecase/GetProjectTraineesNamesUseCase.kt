package domain.usecase

import domain.repository.ProjectRepository
import domain.repository.TeamRepository

class GetProjectTraineesNamesUseCase(
    private val projectRepository: ProjectRepository,
    private val teamRepository: TeamRepository
) : BaseUseCase<String, List<String>> {

    override fun invoke(projectId: String): List<String> {
        val teamId = getTeamIdByProjectId(projectId) ?: return emptyList()
        return getMenteesNameByTeamId(teamId)
    }

    private fun getTeamIdByProjectId(id: String): String? =
        projectRepository.getAllProjects().asSequence().find { it.id == id }?.teamId

    private fun getMenteesNameByTeamId(id: String): List<String> = teamRepository.getAllTeams().find { it.id == id }
        ?.members
        ?.map { it.name }
        ?: emptyList()
}