package hu.training.app.trainingappserver.config

import hu.training.app.trainingappserver.model.*
import hu.training.app.trainingappserver.repository.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class TestDataInitializer(
        private val exerciseRepository: ExerciseRepository,
        private val workoutRepository: WorkoutRepository,
        private val workoutExerciseRepository: WorkoutExerciseRepository,
        private val equipmentRepository: EquipmentRepository,
        private val muscleGroupRepository: MuscleGroupRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val equipments = equipmentRepository.saveAll(
                listOf(
                        Equipment(name = "Dumbbell", image = "dumbbell.jpg"),
                        Equipment(name = "Medicine ball", image = "Medicine_ball.jpg"),
                        Equipment(name = "Pull up bar", image = "pull_up_bar.jpg")
                )
        )

        val muscleGroups = muscleGroupRepository.saveAll(
                listOf(
                        MuscleGroup(name = "Chest"),
                        MuscleGroup(name = "Biceps"),
                        MuscleGroup(name = "Triceps"),
                        MuscleGroup(name = "Legs"),
                        MuscleGroup(name = "Abs")
                )
        )

        val exercises = exerciseRepository.saveAll(
                listOf(
                        Exercise(name = "Push Up", image = "image-1.jpg", video = "video-1.mov", description = "Push up is a very basic body weight exercise to strengthen chest muscles.", equipments = mutableSetOf(equipments[0], equipments[2]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[1], muscleGroups[3])),
                        Exercise(name = "Pull Up", image = "image-2.jpg", video = "video-2.mov", description = "Pull up is a very basic body weight exercise to strengthen upper back muscles.", equipments = mutableSetOf(equipments[1], equipments[2]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[2])),
                        Exercise(name = "Sit Ups", image = "image-3.jpg", video = "video-3.mov", description = "Sit ups is a very basic body weight exercise to strengthen abdominal muscles.", equipments = mutableSetOf(equipments[0], equipments[2]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[1], muscleGroups[2])),
                        Exercise(name = "Dead Lift", image = "image-4.jpg", video = "video-4.mov", description = "Dead Lift is a gym exercise to strengthen back muscles.", equipments = mutableSetOf(equipments[2]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[4], muscleGroups[3])),
                        Exercise(name = "Biceps Curls", image = "image-5.jpg", video = "video-5.mov", description = "Biceps Curls is a basic exercise with a dumbbell to strengthen gain larger biceps.", equipments = mutableSetOf(equipments[1], equipments[2]), muscleGroups = mutableSetOf(muscleGroups[1], muscleGroups[4]))
                )
        )

        val workouts = workoutRepository.saveAll(
                listOf(
                        Workout(name = "Complex Core", image = "workout-1.img", duration = 38, type = "STRENGTH", intensity = 3),
                        Workout(name = "Complex Chest", image = "workout-2.img", duration = 40, type = "CARDIO", intensity = 1),
                        Workout(name = "Complex Legs", image = "workout-3.img", duration = 13, type = "STRENGTH", intensity = 2)
                )
        )

        workoutExerciseRepository.saveAll(
                listOf(
                        WorkoutExercise(workout = workouts[0], exercise = exercises[0], amount = 20, type = "REPS"),
                        WorkoutExercise(workout = workouts[0], exercise = exercises[1], amount = 10, type = "REPS"),
                        WorkoutExercise(workout = workouts[0], exercise = exercises[2], amount = 30, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[0], amount = 55, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[1], amount = 13, type = "REPS"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[2], amount = 5, type = "REPS"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[3], amount = 35, type = "INTERVAL")
                )
        )

        val we = WorkoutExercise(workout = workouts[2], exercise = exercises[4], amount = 12, type = "REPS")

        workouts[2].exercises.plus(we)
        exercises[4].workouts.plus(we)

        workoutRepository.saveAll(workouts)
        exerciseRepository.saveAll(exercises)
    }
}
