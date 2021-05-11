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
                        Equipment(name = "Bodyweight", image = "bodyweight-img.jpg"),
                        Equipment(name = "Dumbbell", image = "dumbbell-img.jpg"),
                        Equipment(name = "Medicine ball", image = "medicine-ball-img.jpg"),
                        Equipment(name = "TRX", image = "trx-img.jpg")
                )
        )

        val muscleGroups = muscleGroupRepository.saveAll(
                listOf(
                        MuscleGroup(name = "chest"),
                        MuscleGroup(name = "biceps"),
                        MuscleGroup(name = "triceps"),
                        MuscleGroup(name = "leg"),
                        MuscleGroup(name = "abs")
                )
        )

        val exercises = exerciseRepository.saveAll(
                listOf(
                        Exercise(name = "Ab Rollups", image = "exercise-img-1.png", video = "ab-rollups.mov", description = "Push up is a very basic body weight exercise to strengthen chest muscles.", equipments = mutableSetOf(equipments[2]), muscleGroups = mutableSetOf(muscleGroups[4])),
                        Exercise(name = "Bicycle Crunches", image = "exercise-img-2.png", video = "bicycle-crunches.mov", description = "Pull up is a very basic body weight exercise to strengthen upper back muscles.", equipments = mutableSetOf(equipments[2]), muscleGroups = mutableSetOf(muscleGroups[3], muscleGroups[4])),
                        Exercise(name = "Abdominal Hip Raises", image = "exercise-img-3.png", video = "abdominal-hip-raises.mov", description = "Sit ups is a very basic body weight exercise to strengthen abdominal muscles.", equipments = mutableSetOf(equipments[3]), muscleGroups = mutableSetOf(muscleGroups[3])),
                        Exercise(name = "Narrow Push Ups", image = "exercise-img-4.png", video = "narrow-push-up.mov", description = "Dead Lift is a gym exercise to strengthen back muscles.", equipments = mutableSetOf(equipments[0]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[3])),
                        Exercise(name = "Kneeled Push Ups", image = "exercise-img-5.png", video = "kneeled-push-ups.mov", description = "Biceps Curls is a basic exercise with a dumbbell to strengthen gain larger biceps.", equipments = mutableSetOf(equipments[0]), muscleGroups = mutableSetOf(muscleGroups[0], muscleGroups[3])),
                        Exercise(name = "Crunches", image = "exercise-img-6.png", video = "crunches.mov", description = "Crunches are very good exercises to strenthen the core muscles.", equipments = mutableSetOf(equipments[0]), muscleGroups = mutableSetOf(muscleGroups[4])),
                        Exercise(name = "Crunches with dumbbel", image = "exercise-img-6.png", video = "crunches.mov", description = "Crunches are very good exercises to strenthen the core muscles.", equipments = mutableSetOf(equipments[1]), muscleGroups = mutableSetOf(muscleGroups[4]))
                )
        )

        val workouts = workoutRepository.saveAll(
                listOf(
                        Workout(name = "Complex Core", image = "workout-img-1.png", duration = 38, type = "STRENGTH", intensity = 3),
                        Workout(name = "Light Cardio", image = "workout-img-2.png", duration = 40, type = "CARDIO", intensity = 1),
                        Workout(name = "Strong Back", image = "workout-img-3.png", duration = 13, type = "CARDIO", intensity = 2),
                        Workout(name = "Build Triceps", image = "workout-img-4.png", duration = 30, type = "STRENGTH", intensity = 3),
                        Workout(name = "TRX Power Sets", image = "workout-img-5.png", duration = 10, type = "CARDIO", intensity = 1),
                        Workout(name = "Epic Gym Day", image = "workout-img-6.png", duration = 56, type = "STRENGTH", intensity = 2)
                )
        )

        workoutExerciseRepository.saveAll(
                listOf(
                        WorkoutExercise(workout = workouts[0], exercise = exercises[0], amount = 20, type = "REP"),
                        WorkoutExercise(workout = workouts[0], exercise = exercises[5], amount = 10, type = "REP"),
                        WorkoutExercise(workout = workouts[0], exercise = exercises[2], amount = 10, type = "INTERVAL"),

                        WorkoutExercise(workout = workouts[1], exercise = exercises[0], amount = 55, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[1], amount = 13, type = "REP"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[2], amount = 5, type = "REP"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[3], amount = 35, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[1], exercise = exercises[5], amount = 35, type = "INTERVAL"),

                        WorkoutExercise(workout = workouts[2], exercise = exercises[2], amount = 55, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[2], exercise = exercises[3], amount = 13, type = "REP"),
                        WorkoutExercise(workout = workouts[2], exercise = exercises[5], amount = 35, type = "INTERVAL"),

                        WorkoutExercise(workout = workouts[3], exercise = exercises[4], amount = 55, type = "INTERVAL"),
                        WorkoutExercise(workout = workouts[3], exercise = exercises[3], amount = 13, type = "REP"),

                        WorkoutExercise(workout = workouts[4], exercise = exercises[2], amount = 55, type = "INTERVAL"),

                        WorkoutExercise(workout = workouts[5], exercise = exercises[6], amount = 13, type = "REP")
                )
        )

        val we = WorkoutExercise(workout = workouts[2], exercise = exercises[4], amount = 12, type = "REPS")

        workouts[2].exercises.plus(we)
        exercises[4].workouts.plus(we)

        workoutRepository.saveAll(workouts)
        exerciseRepository.saveAll(exercises)
    }
}
