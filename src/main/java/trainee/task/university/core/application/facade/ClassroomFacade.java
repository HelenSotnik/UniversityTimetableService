package trainee.task.university.core.application.facade;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.ClassroomDto;
import trainee.task.university.core.mapper.ClassroomMapper;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.domain.service.ClassroomService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassroomFacade {

    private final ClassroomService classroomService;
    private final ClassroomMapper classroomMapper;

    public ClassroomFacade(ClassroomService classroomService, ClassroomMapper classroomMapper) {
        this.classroomService = classroomService;
        this.classroomMapper = classroomMapper;
    }

    public ClassroomDto findClassroomById(Long id) throws NotFoundException {
        ClassroomModel classroomModel = classroomService.findById(id);
        return classroomMapper.modelToDto(classroomModel);
    }

    public List<ClassroomDto> findAllClassrooms() {
        List<ClassroomModel> classroomModelList = classroomService.findAll();
        return classroomModelList.stream().map(classroomMapper::modelToDto).collect(Collectors.toList());
    }

    public void delete(Long id) throws NotFoundException {
        classroomService.delete(id);
    }

    public ClassroomDto save(ClassroomDto classroomDto) {
        ClassroomModel classroomModel = classroomMapper.dtoToModel(classroomDto);
        return classroomMapper.modelToDto(classroomService.save(classroomModel));
    }

    public ClassroomDto update(Long id, ClassroomDto classroomDto) throws NotFoundException {
        ClassroomModel classroomModel = classroomMapper.dtoToModel(classroomDto);
        return classroomMapper.modelToDto(classroomService.update(id, classroomModel));
    }
}
