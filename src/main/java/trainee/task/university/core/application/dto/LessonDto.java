package trainee.task.university.core.application.dto;

import lombok.Getter;
import lombok.Setter;
import trainee.task.university.core.database.entity.LessonType;

@Getter
@Setter
public class LessonDto {
    private Long id;
    private LessonType lessonType;
    private String subjectName;
    private ClassroomDto classroom;
}
