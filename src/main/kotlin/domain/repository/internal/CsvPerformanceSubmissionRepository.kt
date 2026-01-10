package domain.repository.internal

import domain.model.PerformanceSubmission
import domain.repository.contracts.PerformanceSubmissionRepository
import datasource.EcosystemDatasource
import domain.repository.contracts.MenteeRepository
import domain.repository.mapping.DomainMapper

class CsvPerformanceSubmissionRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper,
    private val menteeRepository: MenteeRepository
) : PerformanceSubmissionRepository {

    override fun getAllPerformanceSubmission(): List<PerformanceSubmission> {
        val rawData = datasource.getAllPerformanceSubmissions()
        return mapper.mapPerformanceSubmissionsRawToDomainList(rawData)
    }
}
