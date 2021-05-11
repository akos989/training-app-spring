package hu.training.app.trainingappserver.controller

import hu.training.app.trainingappserver.dto.EquipmentDto
import hu.training.app.trainingappserver.service.EquipmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8100"], maxAge = 3600)
@RestController
@RequestMapping(EquipmentController.BASE_URL)
class EquipmentController(
        private val equipmentService: EquipmentService
) {
    companion object {
        const val BASE_URL = "/api/equipments"
    }

    @GetMapping
    fun getEquipment(): ResponseEntity<List<EquipmentDto>> {
        return ResponseEntity.ok(equipmentService.getEquipments())
    }

    @PostMapping
    fun save(@RequestBody equipmentDto: EquipmentDto): ResponseEntity<EquipmentDto> {
        return ResponseEntity.ok(equipmentService.saveEquipment(equipmentDto))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody equipmentDto: EquipmentDto): ResponseEntity<EquipmentDto> {
        return ResponseEntity.ok(equipmentService.update(id, equipmentDto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        equipmentService.deleteEquipment(id)
        return ResponseEntity.ok(Unit)
    }
}
