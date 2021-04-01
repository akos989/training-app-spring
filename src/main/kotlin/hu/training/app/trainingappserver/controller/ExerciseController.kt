package hu.training.app.trainingappserver.controller

import hu.training.app.trainingappserver.model.Exercise
import hu.training.app.trainingappserver.service.ExerciseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ExerciseController.BASE_URL)
class ExerciseController(
        private val exerciseService: ExerciseService
) {

    companion object {
        const val BASE_URL = "/api/exercises"
    }

    @GetMapping
    fun getExercises(): ResponseEntity<List<Exercise>> {
        return ResponseEntity.ok(exerciseService.getExercises())
    }
}
