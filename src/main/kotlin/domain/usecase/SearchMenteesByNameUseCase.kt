package domain.usecase

import domain.model.Mentee
import domain.repository.MenteeRepository

class SearchMenteesByNameUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<String, List<Mentee>> {

    override fun invoke(menteeName: String): List<Mentee> {
        if (menteeName.isBlank()) return emptyList()
        return menteeRepository
            .getAllMentees()
            ?.filter { it.findTheMenteeName(menteeName) } ?: emptyList()
    }

    private fun Mentee.findTheMenteeName(query: String): Boolean {
        return name?.contains(query, ignoreCase = true) ?: false
    }
}
