package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.MuscleGroup
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken

class MuscleGroupDto(
        var name: String = ""
)

fun List<MuscleGroup>.toDTO(mapper: ModelMapper): List<MuscleGroupDto> {
    val listType = object : TypeToken<List<MuscleGroupDto>>() {}.type
    return mapper.map(this, listType)
}

fun MuscleGroup.toDTO(mapper: ModelMapper): MuscleGroupDto =
        mapper.map(this, MuscleGroupDto::class.java)

fun Set<MuscleGroup>.toStringDTO(): List<String> {
    return this.map {
        it.name
    }
}
