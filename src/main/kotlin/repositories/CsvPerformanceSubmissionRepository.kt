package repositories

import datasource.EcosystemDatasource
import domain.PerformanceSubmission
import models.PerformanceSubmissionRaw

class CsvPerformanceSubmissionRepository(private val datasource: EcosystemDatasource) :
    PerformanceSubmissionRepository {
    fun mappingPerformanceSubmissionRawToPerformanceSubmission(
        dataPerformanceSubmissions: List<PerformanceSubmissionRaw>
    ): List<PerformanceSubmission> {
        return dataPerformanceSubmissions.map { performanceSubmissionRaw ->
            PerformanceSubmission(
                performanceSubmissionRaw.id,
                performanceSubmissionRaw.type,
                performanceSubmissionRaw.score,
                performanceSubmissionRaw.menteeId
            )
        }
    }

    override fun getAllPerformanceSubmission(): List<PerformanceSubmission> {
        val dataAllPerformanceSubmission = datasource.getAllPerformanceSubmissionRaw()
        return mappingPerformanceSubmissionRawToPerformanceSubmission(
            dataAllPerformanceSubmission
        )
    }
}