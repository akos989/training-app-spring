package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.Workout
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken

class WorkoutDto(
    var name: String = "",
    var image: String = "",
    var duration: Int = 0,
    var type: String = "STRENGTH",
    var muscleGroups: List<String> = listOf(),
    var equipments: List<String> = listOf(),
    var intensity: Int = 1,
    var exercises: List<ExerciseDto> = listOf()
)

fun List<Workout>.toDTO(mapper: ModelMapper): List<WorkoutDto> {
    return this.map {
        it.toDTO(mapper)
    }
}

fun Workout.toDTO(mapper: ModelMapper): WorkoutDto {
    val mapped = mapper.map(this, WorkoutDto::class.java)
    mapped.exercises = this.exercises.map {
        it.exercise.toDTO(mapper)
    }
    return mapped
}
