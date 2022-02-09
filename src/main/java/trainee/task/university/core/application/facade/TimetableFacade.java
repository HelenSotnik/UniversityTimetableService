package trainee.task.university.core.application.facade;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.TimetableDto;
import trainee.task.university.core.database.entity.DayOfTheWeek;
import trainee.task.university.core.mapper.TimetableMapper;
import trainee.task.university.core.domain.model.TimetableModel;
import trainee.task.university.core.domain.service.TimetableService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimetableFacade {

    private final TimetableService timetableService;
    private final TimetableMapper timetableMapper;

    public TimetableFacade(TimetableService timetableService, TimetableMapper timetableMapper) {
        this.timetableService = timetableService;
        this.timetableMapper = timetableMapper;
    }

    public TimetableDto findTimetableById(Long id) throws NotFoundException {
        TimetableModel timetableModel = timetableService.findById(id);
        return timetableMapper.modelToDto(timetableModel);
    }

    public List<TimetableDto> findAllTimetables() {
        List<TimetableModel> timetableModelList = timetableService.findAll();
        return timetableModelList.stream().map(timetableMapper::modelToDto).collect(Collectors.toList());
    }

    public void delete(Long id) throws NotFoundException {
        timetableService.delete(id);
    }

    public TimetableDto save(TimetableDto timetableDto) {
        TimetableModel timetableModel = timetableMapper.dtoToModel(timetableDto);
        return timetableMapper.modelToDto(timetableService.save(timetableModel));
    }

    public TimetableDto update(Long id, TimetableDto timetableDto) throws NotFoundException {
        TimetableModel timetableModel = timetableMapper.dtoToModel(timetableDto);
        return timetableMapper.modelToDto(timetableService.update(id, timetableModel));
    }

    public TimetableDto findByStudentGroupNameAndDayOfTheWeek(String groupName, DayOfTheWeek dayOfTheWeek) throws NotFoundException{
        TimetableModel timetableModel = timetableService.findByStudentGroupNameAndDayOfTheWeek(groupName,dayOfTheWeek);
        return timetableMapper.modelToDto(timetableModel);
    }
}
