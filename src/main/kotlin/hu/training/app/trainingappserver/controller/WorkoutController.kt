package hu.training.app.trainingappserver.controller

import hu.training.app.trainingappserver.dto.WorkoutDto
import hu.training.app.trainingappserver.service.WorkoutService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(WorkoutController.BASE_URL)
class WorkoutController(
        private val workoutService: WorkoutService
) {

    companion object {
        const val BASE_URL = "/api/workouts"
    }

    @GetMapping
    fun getWorkouts(): ResponseEntity<List<WorkoutDto>> {
        return ResponseEntity.ok(workoutService.getWorkouts())
    }

    @PostMapping
    fun save(@RequestBody workoutDto: WorkoutDto): ResponseEntity<WorkoutDto> {
        return ResponseEntity.ok(workoutService.saveWorkout(workoutDto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        workoutService.deleteWorkout(id)
        return ResponseEntity.ok(Unit)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updatedWorkoutDto: WorkoutDto): ResponseEntity<WorkoutDto> {
        return ResponseEntity.ok(workoutService.update(id, updatedWorkoutDto))
    }
}
