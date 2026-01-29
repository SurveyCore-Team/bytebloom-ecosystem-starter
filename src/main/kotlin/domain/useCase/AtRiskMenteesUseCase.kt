package domain.useCase
import domain.usecase.BaseUseCase
import domain.model.Mentee
class AtRiskMenteesUseCase : BaseUseCase<List<Mentee>, List<Mentee>> {
    override operator fun invoke(input: List<Mentee>): List<Mentee> {
        return input.filter { mentee ->
            val absences =
                mentee.attendanceRecords.count { it.status != "Present" }
            val averageScore =
                mentee.submissions.map { it.score }.average()
            absences >= 2 && averageScore < 60
        }
    }
}
