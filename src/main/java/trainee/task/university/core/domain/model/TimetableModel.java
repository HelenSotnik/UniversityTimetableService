package trainee.task.university.core.domain.model;

import lombok.Getter;
import lombok.Setter;
import trainee.task.university.core.database.entity.DayOfTheWeek;

import java.util.List;

@Getter
@Setter
public class TimetableModel {
    private Long id;
    private DayOfTheWeek dayOfTheWeek;
    private List<LessonModel> lessons;
}
