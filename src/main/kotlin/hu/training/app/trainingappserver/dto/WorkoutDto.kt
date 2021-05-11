package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.Workout
import org.modelmapper.ModelMapper

class WorkoutDto(
        var name: String = "",
        var image: String = "",
        var duration: Int = 0,
        var type: String = "STRENGTH",
        var muscleGroups: String = "",
        var equipments: String = "",
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
        val mappedExerciseDto = it.exercise.toDTO(mapper)
        mappedExerciseDto.amount = it.amount
        mappedExerciseDto.type = it.type
        mappedExerciseDto
    }
    val equipments = mutableListOf<String>()
    mapped.exercises.forEach {
        equipments.addAll(it.equipments)
    }

    mapped.equipments = equipments.toSet().joinToString()

    val muscleGroups = mutableListOf<String>()
    mapped.exercises.forEach {
        muscleGroups.addAll(it.muscleGroups)
    }
    mapped.muscleGroups = muscleGroups.toSet().joinToString()
    return mapped
}
