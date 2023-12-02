package Day1

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(){
    val list = readFile("/Users/windowsight/AdventOfCode/src/main/kotlin/Day1/Trebuchet.txt")
    if (!list.isNullOrEmpty()){
        val total = sumTotalConcatenateNumbersList(list)
        println(total)
    }else{
        println("Fichero vacio o no existe")
    }


}

fun sumTotalConcatenateNumbersList(list: List<String>): Int{
    return list.sumOf { concatenateNumbers(it) }
}

fun concatenateNumbers(s : String): Int{
    val stringOnlyDigits = transformDigitsWithLettersToNumbers(s).filter { it.isDigit() }
    val charFirst = stringOnlyDigits.first()
    val charLast = stringOnlyDigits.last()
    return "$charFirst$charLast".toInt()
    }

fun transformDigitsWithLettersToNumbers(s: String): String {
    val spellNumbers  = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var ret = s

    spellNumbers.forEach(){spellNumber ->
        var multipleNumbers = false
        while (!multipleNumbers){
            if (ret.contains(spellNumber)){
                val position = ret.indexOf(spellNumber)+2
                ret = addStringAtPosition(ret, position,spellNumber)
            }else{
                multipleNumbers = true
            }
        }


    }
    return ret
}

fun addStringAtPosition(originalString: String, position: Int, stringToAdd: String): String {
    val numberToAdd = when(stringToAdd){
        "one"->"1"
        "two"->"2"
        "three"->"3"
        "four"->"4"
        "five"->"5"
        "six"->"6"
        "seven"->"7"
        "eight"->"8"
        "nine"->"9"
        else -> ""
    }
    return originalString.substring(0, position) + numberToAdd + originalString.substring(position)
}


fun readFile (fileName: String): List<String>?{
    try {
        val file = File(fileName)

        if (file.exists()) {
            val reader = BufferedReader(FileReader(file))
            val lines = mutableListOf<String>()

            var linea: String?

            while (reader.readLine().also { linea = it } != null) {
                lines.add(linea.orEmpty())
            }

            reader.close()

            return lines
        } else {
            println("El archivo no existe.")
        }
    } catch (e: Exception) {
        println("Ocurrió un error al leer el archivo: ${e.message}")
    }
    return null
}


//54418
