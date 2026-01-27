package domain.usecase.attendance

import domain.model.Mentee
import domain.repository.MenteeRepository
import domain.usecase.BaseUseCase

class FindMenteesWithPerfectAttendanceUseCase(
    private val menteeRepository: MenteeRepository

) : BaseUseCase<Unit, List<Mentee>> {
    override fun invoke(input: Unit): List<Mentee> {
        return menteeRepository.getAllMentees().filter {
            it.hasPerfectAttendance()
        }
    }

    private fun Mentee.hasPerfectAttendance(): Boolean =
        this.attendanceRecords.all { it.status.lowercase() == PRESENT }

    companion object {
        private const val PRESENT = "present"
    }

}
