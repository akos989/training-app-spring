package hu.training.app.trainingappserver.config

import hu.training.app.trainingappserver.model.Exercise
import hu.training.app.trainingappserver.repository.ExerciseRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class TestDataInitializer(
        private val exerciseRepository: ExerciseRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        exerciseRepository.saveAll(
                listOf(
                        Exercise(name = "Push Up", image = "image-1.jpg", video =  "video-1.mov", description = "Push up is a very basic body weight exercise to strengthen chest muscles."),
                        Exercise(name = "Pull Up", image = "image-2.jpg", video =  "video-2.mov", description = "Pull up is a very basic body weight exercise to strengthen upper back muscles."),
                        Exercise(name = "Sit Ups", image = "image-3.jpg", video =  "video-3.mov", description = "Sit ups is a very basic body weight exercise to strengthen abdominal muscles."),
                        Exercise(name = "Dead Lift", image = "image-4.jpg", video =  "video-4.mov", description = "Dead Lift is a gym exercise to strengthen back muscles."),
                        Exercise(name = "Biceps Curls", image = "image-5.jpg", video =  "video-5.mov", description = "Biceps Curls is a basic exercise with a dumbbell to strengthen gain larger biceps."),
                )
        )
    }
}
