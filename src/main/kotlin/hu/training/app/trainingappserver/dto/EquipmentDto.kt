package hu.training.app.trainingappserver.dto

import hu.training.app.trainingappserver.model.Equipment
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken

class EquipmentDto(
        val name: String = "",
        val image: String = ""
)

fun List<Equipment>.toDTO(mapper: ModelMapper): List<EquipmentDto> {
    val listType = object : TypeToken<List<EquipmentDto>>() {}.type
    return mapper.map(this, listType)
}

fun Equipment.toDTO(mapper: ModelMapper): EquipmentDto =
        mapper.map(this, EquipmentDto::class.java)


fun Set<Equipment>.toStringDTO(): List<String> {
    return this.map {
        it.name
    }
}
