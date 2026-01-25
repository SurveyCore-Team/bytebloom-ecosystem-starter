package domain.model

data class PerformanceSubmission(
    val id: String,
    val type: String,
    val score: Double,
    val menteeId: String
)