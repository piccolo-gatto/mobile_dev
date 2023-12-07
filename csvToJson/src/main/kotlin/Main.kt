import com.google.gson.Gson
import com.google.gson.JsonObject
import com.opencsv.CSVReaderBuilder
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

data class App(
    val name: String,
    val category: String,
    val rating: String,
    val reviews: String,
    val size: String,
    val installs: Int,
    val type: String,
    val price: Boolean,
    val contentRating: String,
    val genres: List<String>,
    val lastUpdated: String,
    val currentVer: String,
    val androidVer: Int
)

fun parseCsvLine(line: Array<String>): App {
    return App(
        line[0],
        line[1],
        line[2],
        line[3],
        line[4],
        installsToNumber(line[5]),
        line[6],
        priceToBool(line[7]),
        line[8],
        line[9].split(";"),
        formatDate(line[10]),
        line[11],
        androidVersionToApi(line[12])
    )
}

fun priceToBool(price: String) : Boolean {
    return price != "0"
}

fun installsToNumber(installs: String): Int {
    val numberPattern = Regex("\\d+")
    val numberString = numberPattern.findAll(installs).joinToString("") { it.value }
    return numberString.toIntOrNull() ?: 0
}

fun formatDate(date: String): String {
    val inputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val parsedDate = inputFormat.parse(date)
    return outputFormat.format(parsedDate)
}


fun androidVersionToApi(androidVer: String): Int {
    val versionRegex = "\\d+(\\.\\d+)?".toRegex()
    val version = versionRegex.find(androidVer)?.value ?: "0"
    return when (version.toDouble()) {
        in 1.0..1.5 -> 3
        in 1.5..1.6 -> 4
        in 2.0..2.1 -> 7
        in 2.2..2.3 -> 8
        in 2.3..3.0 -> 10
        in 3.0..4.0 -> 11
        in 4.0..4.1 -> 14
        in 4.1..4.2 -> 16
        in 4.2..4.3 -> 17
        in 4.3..4.4 -> 18
        in 4.4..5.0 -> 19
        in 5.0..5.1 -> 21
        in 5.1..6.0 -> 22
        in 6.0..7.0 -> 23
        in 7.0..7.1 -> 24
        in 7.1..8.0 -> 25
        in 8.0..8.1 -> 26
        in 8.1..9.0 -> 27
        in 9.0..10.0 -> 28
        in 10.0..11.0 -> 29
        in 11.0..12.0 -> 30
        in 12.0..13.0 -> 31
        in 13.0..14.0 -> 33
        in 14.0..15.0 -> 34
        else -> 1
    }
}

fun main() {
    val csv = "src/main/resources/googleplaystore.csv"
    val apps = mutableListOf<App>()

    val reader = CSVReaderBuilder(FileReader(csv)).withSkipLines(1).build()

    reader.use { csvReader ->
        csvReader.forEach { line ->
            try {
                val app = parseCsvLine(line)
                apps.add(app)
            } catch (e:Exception){
                println("")
            }
        }
    }
    val json = JsonObject()
    val groupedApps = apps.groupBy { it.category }
    groupedApps.forEach { (category, appsInCategory) ->
        val jsonCategory = JsonObject()
        jsonCategory.addProperty("category", category)
        jsonCategory.add("apps", Gson().toJsonTree(appsInCategory))
        json.add(category, jsonCategory)
    }
    val out = "src/main/resources/out.json"
    FileWriter(out).use { fileWriter ->
        Gson().toJson(json, fileWriter)
    }

    println("Output $out")
}