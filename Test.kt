import java.awt.GraphicsConfigTemplate
import java.util.*

fun main(args: Array<String>){
    println("Hello, Chris!")
    feedTheFish()
    eagerExample()

    var bubbles = 0
    while (bubbles < 50){
        bubbles++
    }

    repeat(2){
        println("A fish is swimming")
    }
}

fun eagerExample(){
    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")
    val eager = decorations.filter {it[0] == 'p'}
    println(eager)

    // apply filter lazy
    val filtered = decorations.asSequence().filter{it[0]=='p'}
    println(filtered)
    println(filtered.toList())

    //apply map lazily
    val lazyMap = decorations.asSequence().map {
        println("map: $it")
            it
    }
    println(lazyMap)
    println("first: ${lazyMap.first()}")
    println("all: ${lazyMap.toList()}")
}

fun feedTheFish(){
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")

    shouldChangeWater(day, 20, 22)
    shouldChangeWater(day)
    shouldChangeWater(day, dirty=50)
    swim(50, speed="slow")

    if(shouldChangeWater(day)) {
        println("Change the water today ")
    }
}

fun swim(time: Int,speed: String= "fast"){
    println("swimming $speed!")
}

fun getDirtySensorReading() = 20

fun shouldChangeWater(day: String,
                      temperature: Int = 22,
                      dirty: Int = getDirtySensorReading()) : Boolean{
    val isTooHot = temperature >30
    val isDirty = dirty > 30
    val isSunday = day == "Sunday"

    return when{
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else -> false
    }
}

var dirty = 20

val waterFilter: (Int) -> Int = {dirty -> dirty/2}
fun feedFish(dirty: Int) = dirty + 10

fun updateDirty(dirty: Int, operation: (Int) -> Int): Int{
    return operation(dirty)
}

fun dirtyProcessor(){
    dirty = updateDirty(dirty, waterFilter)
    dirty = updateDirty(dirty, ::feedFish)
    dirty = updateDirty(dirty, {dirty -> dirty + 50})
}

fun isTooHot(temperature: Int) : Boolean = temperature > 30

fun isDirty(dirty: Int) : Boolean = dirty > 30

fun isSunday(day: String) = day == "Sunday"

fun randomDay() : String {
    val week = listOf ("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random().nextInt(7)]
}

fun fishFood (day : String) : String {
    var food = "fasting"

    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pellets"
        "Wednesday" -> "redworms"
        "Thursday" -> "grandules"
        "Friday" -> "Mosquitoes"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "fasting"
    }
    return food
}