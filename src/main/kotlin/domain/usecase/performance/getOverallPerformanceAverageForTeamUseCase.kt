package domain.usecase.performance
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class getOverallPerformanceAverageForTeamUseCase(
    private val teamRepository: TeamRepository
): BaseUseCase<String , Double> {
     override fun invoke(teamId: String): Double{
        return teamRepository.getAllTeams()
            .first { it.id == teamId }
            .members
            .flatMap { it.submissions }
            .map { it.score }
            .average()
    }
}

