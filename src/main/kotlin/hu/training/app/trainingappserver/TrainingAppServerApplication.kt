package hu.training.app.trainingappserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrainingAppServerApplication

fun main(args: Array<String>) {
    runApplication<TrainingAppServerApplication>(*args)
}
