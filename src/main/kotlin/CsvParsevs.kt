import  com.bytebloom.model.raw.PerformanceRaw
import java.io.File
fun parsePerformanceData():List<PerformanceRaw> {
    val file = File("src/main/resources/performance.csv")
    val lines = file.readLines().drop(1)
    val space = lines.map { line ->
        val space1 = line.split(",")
        PerformanceRaw( menteeId=space1[0],
                submissionId=space1[1],
                submissionType=space1[2],
                score=space1[3] )

        
    }

    return space
    
}
