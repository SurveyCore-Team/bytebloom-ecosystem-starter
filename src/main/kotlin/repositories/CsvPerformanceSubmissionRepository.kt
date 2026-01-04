package repositories

import datasource.EcosystemDatasource
import domain.PerformanceSubmission
import domain.repository.mapping.DomainMapper
class CsvPerformanceSubmissionRepository(private val datasource: EcosystemDatasource,private val mapper: DomainMapper) :
    PerformanceSubmissionRepository {

    override fun getAllPerformanceSubmission(): List<PerformanceSubmission> {
        val dataAllPerformanceSubmission = datasource.getAllPerformanceSubmissions()
        return mapper.mapPerformanceSubmissionsRawToDomainList(
            dataAllPerformanceSubmission
        )
    }
}