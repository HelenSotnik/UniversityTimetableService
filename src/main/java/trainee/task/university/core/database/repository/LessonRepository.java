package trainee.task.university.core.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainee.task.university.core.database.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

}
