package trainee.task.university.core.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainee.task.university.core.database.entity.DayOfTheWeek;
import trainee.task.university.core.database.entity.Timetable;

import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    Optional<Timetable> findByStudent_GroupNameAndDayOfTheWeek(String groupName, DayOfTheWeek dayOfTheWeek);

}
