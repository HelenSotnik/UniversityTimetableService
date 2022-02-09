package trainee.task.university.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.ClassroomDto;
import trainee.task.university.core.database.entity.Classroom;
import trainee.task.university.core.domain.model.ClassroomModel;

import java.util.Objects;

@Component
public class ClassroomMapper {

    private final ModelMapper mapper;

    public ClassroomMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ClassroomModel dtoToModel(ClassroomDto classroomDto) {
        ClassroomModel classroomModel = mapper.map(classroomDto, ClassroomModel.class);
        return classroomModel;
    }

    public ClassroomModel entityToModel(Classroom classroom) {
        return Objects.isNull(classroom) ? null : mapper.map(classroom, ClassroomModel.class);
    }

    public ClassroomDto modelToDto(ClassroomModel classroomModel) {
        return Objects.isNull(classroomModel) ? null : mapper.map(classroomModel, ClassroomDto.class);
    }

    public Classroom modelToEntity(ClassroomModel classroomModel) {
        Classroom classroom = mapper.map(classroomModel, Classroom.class);
        return classroom;
    }
}
