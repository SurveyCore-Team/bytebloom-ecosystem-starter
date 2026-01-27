package domain.usecase.attendance

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class GetTopMenteesByAttendanceCountUseCase(
    private val menteeRepository: MenteeRepository
) : BaseUseCase<Int, List<Mentee>> {

    override fun invoke(numberOfTopMentees: Int): List<Mentee> {
        return menteeRepository.getAllMentees()
            .sortedByDescending { it.presentCount() }
            .take(numberOfTopMentees)
    }

    private fun Mentee.presentCount(): Int {
        return attendanceRecords.count { it.status.lowercase() == PRESENT }
    }

    companion object {
        private const val PRESENT = "present"
    }
}