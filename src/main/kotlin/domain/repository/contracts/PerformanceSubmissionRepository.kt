package domain.repository.contracts

import domain.model.PerformanceSubmission

interface PerformanceSubmissionRepository {
    fun getAllPerformanceSubmission(): List<PerformanceSubmission>
}