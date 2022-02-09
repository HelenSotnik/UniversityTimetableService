package trainee.task.university.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.LessonDto;
import trainee.task.university.core.database.entity.Lesson;
import trainee.task.university.core.domain.model.LessonModel;

import java.util.Objects;

@Component
public class LessonMapper {

    private final ModelMapper mapper;

    public LessonMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LessonDto entityToDto(Lesson lesson) {
        return Objects.isNull(lesson) ? null : mapper.map(lesson, LessonDto.class);
    }

    public LessonModel dtoToModel(LessonDto lessonDto) {
        LessonModel lessonModel = mapper.map(lessonDto, LessonModel.class);
        return lessonModel;
    }

    public LessonModel entityToModel(Lesson lesson) {
        return Objects.isNull(lesson) ? null : mapper.map(lesson, LessonModel.class);
    }

    public LessonDto modelToDto(LessonModel lessonModel) {
        return Objects.isNull(lessonModel) ? null : mapper.map(lessonModel, LessonDto.class);
    }

    public Lesson modelToEntity(LessonModel lessonModel) {
        Lesson lesson = mapper.map(lessonModel, Lesson.class);
        return lesson;
    }
}
