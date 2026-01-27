package domain.usecase.attendance

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class FindMenteesWithPoorAttendanceUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Int, List<Mentee>> {
    override fun invoke(minAbsences: Int): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPoorAttendance(minAbsences)
        }
    }

    private fun Mentee.hasPoorAttendance(minAbsences: Int): Boolean {
        return attendanceRecords.count { it.status.lowercase() == ABSENT } > minAbsences
    }
    companion object {
        private const val ABSENT= "absent"
    }
}

