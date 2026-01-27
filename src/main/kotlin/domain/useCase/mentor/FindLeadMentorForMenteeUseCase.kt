package domain.usecase.mentor

import domain.model.Team
import domain.repository.TeamRepository
import domain.usecase.BaseUseCase

class FindLeadMentorForMenteeUseCase(private val teamRepository: TeamRepository) : BaseUseCase<String, String?> {
    override fun invoke(menteeId: String): String? {
        return teamRepository.getAllTeams()
            .firstOrNull { isMenteeInTeam(it, menteeId) }
            ?.mentorLead
    }

    private fun isMenteeInTeam(team: Team, menteeId: String): Boolean = team.members.any { it.id == menteeId }
}