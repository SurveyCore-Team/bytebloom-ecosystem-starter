package domain.useCase

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class SearchMenteesByNameUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<String, List<Mentee>> {

    override fun invoke(input: String): List<Mentee> {
        return findMenteesByName(input)
    }

    private fun findMenteesByName(query: String): List<Mentee> {
        return menteeRepository
            .getAllMentees()
            .filter { it.findTheMenteeName(query) }
    }

    private fun Mentee.findTheMenteeName(query: String): Boolean {
        return name.contains(query, ignoreCase = true)
    }
}
