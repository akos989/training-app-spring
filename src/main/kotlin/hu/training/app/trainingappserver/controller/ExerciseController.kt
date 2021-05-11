package hu.training.app.trainingappserver.controller

import hu.training.app.trainingappserver.dto.ExerciseDto
import hu.training.app.trainingappserver.service.ExerciseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8100"], maxAge = 3600)
@RestController
@RequestMapping(ExerciseController.BASE_URL)
class ExerciseController(
        private val exerciseService: ExerciseService
) {

    companion object {
        const val BASE_URL = "/api/exercises"
    }

    @GetMapping
    fun getExercises(): ResponseEntity<List<ExerciseDto>> {
        return ResponseEntity.ok(exerciseService.getExercises())
    }

    @PostMapping
    fun save(@RequestBody exerciseDto: ExerciseDto): ResponseEntity<ExerciseDto> {
        return ResponseEntity.ok(exerciseService.saveExercise(exerciseDto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        exerciseService.deleteExercise(id)
        return ResponseEntity.ok(Unit)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updatedExerciseDto: ExerciseDto): ResponseEntity<ExerciseDto> {
        return ResponseEntity.ok(exerciseService.update(id, updatedExerciseDto))
    }
}
