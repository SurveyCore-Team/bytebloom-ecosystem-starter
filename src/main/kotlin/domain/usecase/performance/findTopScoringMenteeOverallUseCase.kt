package domain.usecase.performance
import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class findTopScoringMenteeOverAllUseCase(
    private val menteeRepository: MenteeRepository
): BaseUseCase<Unit, Mentee?> {
    override fun invoke(input: Unit): Mentee? {
        val mentees = menteeRepository.getAllMentees()
        if (mentees.isEmpty()) return null

        return mentees.maxByOrNull { mentee ->
            getMenteeScoresAverage(mentee)
        }

    }
    private fun getMenteeScoresAverage(mentee: Mentee): Double {
        val currentSubmissions = mentee.submissions
        if (currentSubmissions.isEmpty()) return 0.0
        return currentSubmissions.sumOf { it.score } / currentSubmissions.size
    }


}