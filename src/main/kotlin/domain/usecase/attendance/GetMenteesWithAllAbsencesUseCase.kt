package domain.usecase.attendance

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class GetMenteesWithAllAbsencesUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Unit, List<Mentee>> {

    override fun invoke(input: Unit): List<Mentee> {
        return menteeRepository.getAllMentees()
            .filter { it.hasAllAbsences() }
    }

    private fun Mentee.hasAllAbsences(): Boolean {
        return attendanceRecords.isNotEmpty() &&
                attendanceRecords.all { it.status.lowercase() == ABSENT }
    }

    companion object {
        private const val ABSENT = "absent"
    }
}
