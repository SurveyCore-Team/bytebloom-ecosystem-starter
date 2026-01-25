package domain.useCase.mentor

import domain.model.Team
import domain.repository.contracts.TeamRepository

class FindLeadMentorForMenteeUseCase(private val teamRepository: TeamRepository) {
    fun execute(menteeId: String): String? {
        return teamRepository.getAllTeams()
            .first { isMenteeInTeam(it, menteeId) }
            .mentorLead
    }


    private fun isMenteeInTeam(team: Team, menteeId: String): Boolean = team.members.any { it.id == menteeId }
}