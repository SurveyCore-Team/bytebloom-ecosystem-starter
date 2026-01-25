package data.repository

import domain.model.PerformanceSubmission
import domain.repository.PerformanceSubmissionRepository
import data.EcosystemDatasource
import domain.repository.MenteeRepository
import data.mapper.DomainMapper

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
