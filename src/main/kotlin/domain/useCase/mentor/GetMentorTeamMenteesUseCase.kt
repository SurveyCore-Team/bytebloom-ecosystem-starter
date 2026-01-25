package domain.useCase.mentor

import domain.model.Team
import domain.repository.contracts.TeamRepository

class GetMentorTeamMenteesUseCase (private val teamRepository: TeamRepository){
    fun execute(mentorName: String): List<String> {
        return teamRepository.getAllTeams().asSequence()
            .filterByMentor(mentorName)
            .mapToMenteeNames()
            .toList()
    }
    private fun Sequence<Team>.filterByMentor(mentorName: String): Sequence<Team> = filter { it.mentorLead==mentorName }
    private fun Sequence<Team>.mapToMenteeNames(): Sequence<String> = flatMap { it.members.asSequence() }.map { it.name }
}