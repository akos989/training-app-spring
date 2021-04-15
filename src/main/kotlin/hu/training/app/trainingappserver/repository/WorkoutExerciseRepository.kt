package hu.training.app.trainingappserver.repository

import hu.training.app.trainingappserver.model.WorkoutExercise
import hu.training.app.trainingappserver.model.WorkoutExerciseKey
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutExerciseRepository : JpaRepository<WorkoutExercise, WorkoutExerciseKey>
