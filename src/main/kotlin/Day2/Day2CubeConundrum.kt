package Day2

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

private const val maxRed = 12
private const val maxGreen = 13
private const val maxBlue = 14

fun main(){
    val localrute = "C:\\Users\\Pau\\IdeaProjects\\adventOfCode2023\\src\\main\\kotlin\\Day2\\CubeConundrum"
    val inputList = readFile(localrute)
    val splitList = splitOnSpace(inputList.orEmpty())
    val sumPositiveGames = sumPositiveGames(splitList)
    println("Positive Games: $sumPositiveGames")
    val sumPowerGames = sumPowerGames(splitList)
    println("Sum Power Games: $sumPowerGames")

}

fun sumPositiveGames(list :List<List<String>>):Int{
    var sumPositiveGames: Int = 0
    val listMaxColors = calculateMaxColor(list)
    listMaxColors.forEachIndexed { index, game ->
        if(game[0]<= maxRed && game[1]<= maxGreen && game[2]<= maxBlue){
            sumPositiveGames += index+1
        }
    }
    return sumPositiveGames
}

fun sumPowerGames(list :List<List<String>>):Int{
    var sumPowerGames: Int = 0
    val listMaxColors = calculateMaxColor(list)
    listMaxColors.forEachIndexed { _, game ->
        var powerCubeValues = 1
        game.forEachIndexed { _, i ->
            powerCubeValues *= i
        }
        sumPowerGames += powerCubeValues
    }
    return sumPowerGames
}

fun calculateMaxColor(list:List<List<String>>):List<List<Int>>{
    /*red = position 0
    * green = position 1
    * blue = position 2*/
    val listMaxColor:MutableList<List<Int>> = mutableListOf()
    list.forEachIndexed { index, game ->
        val listGame: MutableList<Int> = mutableListOf(0,0,0)
        game.forEachIndexed { index, value ->
            when(value){
                "red"-> listGame[0] = isMaxColor(listGame[0],game[index-1])
                "green" -> listGame[1] = isMaxColor(listGame[1],game[index-1])
                "blue" -> listGame[2] = isMaxColor(listGame[2],game[index-1])
            }
        }
        listMaxColor.add(listGame)
    }
    return listMaxColor
}

fun isMaxColor(listGameNumber: Int, gameValue:String):Int{
    return if(listGameNumber>gameValue.toInt()){listGameNumber}
    else gameValue.toInt()

}

fun splitOnSpace(list: List<String>):List<List<String>>{
    var splitList: MutableList<List<String>> = arrayListOf()

    list.forEach(){
        splitList.add(it.split(" "))
    }
    return splitList
}

fun readFile (fileName: String): List<String>?{
    try {
        val file = File(fileName)

        if (file.exists()) {
            val reader = BufferedReader(FileReader(file))
            val lines = mutableListOf<String>()

            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val lineOrEmpty = line.orEmpty()
                lines.add(lineOrEmpty.substring(8)
                    .replace(",","")
                    .replace(";",""))
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

/**
 * First Start Result = 2331
 * Second Start Result = 71585
 */