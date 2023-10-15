import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    // Create a flow that emits numbers from 1 to 10 at one-second intervals
    val numberFlow = flow {
        for (i in 1..10) {
            delay(1000) // Delay for one second
            emit(i)     // Emit the current number
        }
    }

    // Launch a coroutine to collect and print the numbers from the flow
    val job = GlobalScope.launch {
        numberFlow.collect { number ->
            println("Received: $number")
        }
    }

    // Add a delay to keep the main function running while the flow is collected
    runBlocking {
        delay(11000) // Delay for 11 seconds to allow all numbers to be emitted
        job.cancel() // Cancel the coroutine when done
    }
}
