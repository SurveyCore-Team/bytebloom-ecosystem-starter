package domain.usecase

import domain.model.Team
import domain.repository.ProjectRepository
import domain.repository.TeamRepository

class GetTraineesWithNoProjectsUseCase(
    private val teamRepository: TeamRepository,
    private val projectRepository: ProjectRepository
) : BaseUseCase<Unit, List<String>> {

    override fun invoke(input: Unit): List<String> {
        val assignedTeamIds = getassignedTeamIds()
        return teamRepository.getAllTeams()
            .asSequence().filterTeamsWithoutProjects(assignedTeamIds)
            .extractUniqueMemberNames()
            .toList()
    }

    private fun getassignedTeamIds(): Set<String> = projectRepository.getAllProjects()
        .map { it.teamId }
        .toSet()

    private fun Sequence<Team>.filterTeamsWithoutProjects(assignedIds: Set<String>): Sequence<Team> =
        filter { it.id !in assignedIds }

    private fun Sequence<Team>.extractUniqueMemberNames(): Sequence<String> =
        flatMap { it.members }
            .map { it.name }
            .distinct()
}