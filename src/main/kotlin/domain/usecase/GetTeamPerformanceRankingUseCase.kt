package domain.usecase
import domain.model.Team
import domain.repository.TeamRepository

class GetTeamPerformanceRankingUseCase(
    private val teamRepository: TeamRepository
): BaseUseCase<Unit, List<Pair<Team, Double>>> {
    override fun invoke(input: Unit): List<Pair<Team, Double>> {
        return teamRepository.getAllTeams().asSequence()
            .mapToTeamAverage()
            .sortByAverageDescending()
            .toList()
    }

    private fun Sequence<Team>.mapToTeamAverage(): Sequence<Pair<Team, Double>> =
        map { team ->
            val scores = team.members.asSequence()
                .flatMap { it.submissions.asSequence() }
                .map { it.score }
                .toList()
            val average = if (scores.isEmpty()) 0.0 else scores.average()

            team to average
        }

    private fun Sequence<Pair<Team, Double>>.sortByAverageDescending(): Sequence<Pair<Team, Double>> =
        sortedByDescending { it.second }
}