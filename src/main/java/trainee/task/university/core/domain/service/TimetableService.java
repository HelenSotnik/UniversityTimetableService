package trainee.task.university.core.domain.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import trainee.task.university.core.database.entity.DayOfTheWeek;
import trainee.task.university.core.database.entity.Timetable;
import trainee.task.university.core.database.repository.TimetableRepository;
import trainee.task.university.core.domain.model.TimetableModel;
import trainee.task.university.core.mapper.TimetableMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableService {

    private final TimetableRepository repository;
    private final TimetableMapper mapper;

    public TimetableService(TimetableRepository repository, TimetableMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TimetableModel> findAll() {
        List<Timetable> timetables = repository.findAll();
        List<TimetableModel> timetableList = timetables.stream()
                .map(mapper::entityToModel).collect(Collectors.toList());
        return timetableList;
    }

    public TimetableModel findById(Long id) throws NotFoundException {
        return repository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Timetable not found with id: " + id)));

    }

    public TimetableModel save(TimetableModel timetableModel) {
        Timetable timetable = mapper.modelToEntity(timetableModel);
        return mapper.entityToModel(repository.save(timetable));
    }

    public TimetableModel update(Long id, TimetableModel timetableModel) throws NotFoundException {
        repository.findById(id).map(mapper::entityToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Timetable not found with id: " + id)));
        Timetable timetable = mapper.modelToEntity(timetableModel);
        timetable.setId(id);
        return mapper.entityToModel(repository.save(timetable));
    }

    public void delete(Long id) throws NotFoundException {
        repository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Timetable not found with id: " + id)));
        repository.deleteById(id);
    }

    public TimetableModel findByStudentGroupNameAndDayOfTheWeek(String groupName, DayOfTheWeek dayOfTheWeek) throws NotFoundException {
        return repository.findByStudent_GroupNameAndDayOfTheWeek(groupName, dayOfTheWeek)
                .map(mapper::entityToModel).orElseThrow(() -> new NotFoundException(
                        String.format("Timetable not found for group name: " + groupName + " on " + dayOfTheWeek)));
    }
}
