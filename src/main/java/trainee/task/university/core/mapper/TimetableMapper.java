package trainee.task.university.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.TimetableDto;
import trainee.task.university.core.database.entity.Timetable;
import trainee.task.university.core.domain.model.TimetableModel;

import java.util.Objects;

@Component
public class TimetableMapper {

    private final ModelMapper mapper;

    public TimetableMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public TimetableDto entityToDto(Timetable timetable) {
        return Objects.isNull(timetable) ? null : mapper.map(timetable, TimetableDto.class);
    }

    public TimetableModel dtoToModel(TimetableDto timetableDto) {
        return Objects.isNull(timetableDto) ? null : mapper.map(timetableDto, TimetableModel.class);
    }

    public TimetableModel entityToModel(Timetable timetable) {
        return Objects.isNull(timetable) ? null : mapper.map(timetable, TimetableModel.class);
    }

    public TimetableDto modelToDto(TimetableModel timetableModel) {
        return Objects.isNull(timetableModel) ? null : mapper.map(timetableModel, TimetableDto.class);
    }

    public Timetable modelToEntity(TimetableModel timetableModel) {
        Timetable timetable = mapper.map(timetableModel, Timetable.class);
        return timetable;
    }
}
