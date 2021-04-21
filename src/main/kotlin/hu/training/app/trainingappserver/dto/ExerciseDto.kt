package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.Exercise
import org.modelmapper.ModelMapper

class ExerciseDto(
        var name: String = "",
        var image: String = "",
        var video: String = "",
        var description: String = "",
        var muscleGroups: List<String> = listOf(),
        var equipments: List<String> = listOf()
)

fun List<Exercise>.toDTO(mapper: ModelMapper): List<ExerciseDto> {
    return this.map {
        it.toDTO(mapper)
    }
}

fun Exercise.toDTO(mapper: ModelMapper): ExerciseDto {
    val mapped = mapper.map(this, ExerciseDto::class.java)
    mapped.equipments = this.equipments.toStringDTO()
    mapped.muscleGroups = this.muscleGroups.toStringDTO()
    return mapped
}
