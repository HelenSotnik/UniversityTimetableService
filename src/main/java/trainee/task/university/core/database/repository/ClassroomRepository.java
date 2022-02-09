package trainee.task.university.core.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainee.task.university.core.database.entity.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
