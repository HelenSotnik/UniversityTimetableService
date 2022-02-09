package trainee.task.university.core.application.dto;

import lombok.Getter;
import lombok.Setter;
import trainee.task.university.core.database.entity.DayOfTheWeek;

import java.util.List;

@Getter
@Setter
public class TimetableDto {
    private Long id;
    private DayOfTheWeek dayOfTheWeek;
    private List<LessonDto> lessons;
}
