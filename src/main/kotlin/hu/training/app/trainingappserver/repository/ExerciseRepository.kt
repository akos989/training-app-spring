package hu.training.app.trainingappserver.repository

import hu.training.app.trainingappserver.model.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long> {
}
