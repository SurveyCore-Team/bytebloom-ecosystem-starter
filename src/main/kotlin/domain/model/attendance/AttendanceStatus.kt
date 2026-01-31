package domain.model.attendance

enum class AttendanceStatus {
    PRESENT, ABSENT, LATE, UNKNOWN;

    companion object {
        fun fromString(status: String): AttendanceStatus {
            return entries.find { it.name.equals(status, true) } ?: UNKNOWN
        }
    }
}



