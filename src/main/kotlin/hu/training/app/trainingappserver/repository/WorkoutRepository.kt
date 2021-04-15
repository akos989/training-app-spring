package hu.training.app.trainingappserver.repository

import hu.training.app.trainingappserver.model.Workout
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutRepository : JpaRepository<Workout, Long>
