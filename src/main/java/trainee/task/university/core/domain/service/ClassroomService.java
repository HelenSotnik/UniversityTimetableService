package trainee.task.university.core.domain.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import trainee.task.university.core.database.entity.Classroom;
import trainee.task.university.core.database.repository.ClassroomRepository;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.mapper.ClassroomMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    private final ClassroomRepository repository;
    private final ClassroomMapper mapper;

    public ClassroomService(ClassroomRepository repository, ClassroomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ClassroomModel> findAll() {
        List<Classroom> classrooms = repository.findAll();
        List<ClassroomModel> classroomList = classrooms.stream()
                .map(mapper::entityToModel).collect(Collectors.toList());
        return classroomList;
    }

    public ClassroomModel findById(Long id) throws NotFoundException {
        return repository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Classroom not found with id: " + id)));

    }

    public ClassroomModel save(ClassroomModel classroomModel) {
        Classroom classroom = mapper.modelToEntity(classroomModel);
        return mapper.entityToModel(repository.save(classroom));
    }

    public ClassroomModel update(Long id, ClassroomModel classroomModel) throws NotFoundException {
        repository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Classroom not found with id: " + id)));
        Classroom classroom = mapper.modelToEntity(classroomModel);
        classroom.setId(id);
        return mapper.entityToModel(repository.save(classroom));
    }

    public void delete(Long id) throws NotFoundException {
        repository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Classroom not found with id: " + id)));
        repository.deleteById(id);
    }
}
