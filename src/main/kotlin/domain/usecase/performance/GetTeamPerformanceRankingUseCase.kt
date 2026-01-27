package domain.usecase.performance
import domain.model.Team
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class getTeamPerformanceRankingUseCase(
    private val teamRepository: TeamRepository
): BaseUseCase<Unit, List<Pair<Team, Double>>> {
    override fun invoke(input: Unit): List<Pair<Team, Double>> =
        teamRepository.getAllTeams()
            .asSequence()
            .map { team ->
                team to team.members
                    .asSequence()
                    .flatMap { it.submissions.asSequence() }
                    .map { it.score }
                    .average()
            }
            .sortedByDescending { (_, average) -> average }
            .toList()
}