package trainee.task.university.core.domain.model;

import lombok.Getter;
import lombok.Setter;
import trainee.task.university.core.database.entity.LessonType;

@Getter
@Setter
public class LessonModel {
    private Long id;
    private LessonType lessonType;
    private String subjectName;
    private ClassroomModel classroom;
}
