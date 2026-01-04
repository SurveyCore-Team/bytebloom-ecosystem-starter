package model

data class PerformanceSubmissionRaw(
    val id: String,
    val type:String,
    val score: String,
    val menteeId: String,
)