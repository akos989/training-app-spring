package hu.training.app.trainingappserver.controller

import hu.training.app.trainingappserver.dto.MuscleGroupDto
import hu.training.app.trainingappserver.service.MuscleGroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(MuscleGroupController.BASE_URL)
class MuscleGroupController(
        private val muscleGroupService: MuscleGroupService
) {
    companion object {
        const val BASE_URL = "/api/muscle-groups"
    }

    @GetMapping
    fun getMuscleGroups(): ResponseEntity<List<MuscleGroupDto>> {
        return ResponseEntity.ok(muscleGroupService.getMuscleGroups())
    }

    @PostMapping
    fun save(@RequestBody muscleGroupDto: MuscleGroupDto): ResponseEntity<MuscleGroupDto> {
        return ResponseEntity.ok(muscleGroupService.saveMuscleGroup(muscleGroupDto))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody muscleGroupDto: MuscleGroupDto): ResponseEntity<MuscleGroupDto> {
        return ResponseEntity.ok(muscleGroupService.update(id, muscleGroupDto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        muscleGroupService.deleteMuscleGroup(id)
        return ResponseEntity.ok(Unit)
    }
}
