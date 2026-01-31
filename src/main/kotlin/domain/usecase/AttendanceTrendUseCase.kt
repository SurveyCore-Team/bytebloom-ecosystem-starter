package domain.useCase
import domain.usecase.BaseUseCase
import domain.model.Mentee

class AttendanceTrendUseCase :
    BaseUseCase<List<Mentee>, Map<String, AttendanceTrend>> {
    override fun invoke(
        input: List<Mentee>
    ): Map<String, AttendanceTrend> {
        return input.associate { mentee ->
            val records = mentee.attendanceRecords.map { it.status }
            val trend =
                if (records.size >= 2 &&
                    records.first() == "Present" &&
                    records.last() == "Absent"
                ) {
                    AttendanceTrend.DECLINING
                } else {
                    AttendanceTrend.STABLE_OR_IMPROVING
                }
            mentee.id to trend
        }
    }
}
enum class AttendanceTrend {
    DECLINING,
    STABLE_OR_IMPROVING
}
