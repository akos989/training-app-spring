package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.Equipment
import hu.training.app.trainingappserver.model.Exercise
import hu.training.app.trainingappserver.model.MuscleGroup
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken

class ExerciseDto(
        var name: String = "",
        var image: String = "",
        var video: String = "",
        var description: String = "",
        var muscleGroups: List<String> = listOf(),
        var equipments: List<String> = listOf()
)

fun List<Exercise>.toDTO(mapper: ModelMapper): List<ExerciseDto> {
    val listType = object : TypeToken<List<ExerciseDto>>() {}.type
    return mapper.map(this, listType)
}

fun Exercise.toDTO(mapper: ModelMapper): ExerciseDto =
        mapper.map(this, ExerciseDto::class.java)
