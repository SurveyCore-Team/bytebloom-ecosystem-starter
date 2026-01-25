package domain.repository

import domain.model.PerformanceSubmission

interface PerformanceSubmissionRepository {
    fun getAllPerformanceSubmission(): List<PerformanceSubmission>
}