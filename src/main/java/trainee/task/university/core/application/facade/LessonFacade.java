package trainee.task.university.core.application.facade;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.LessonDto;
import trainee.task.university.core.mapper.LessonMapper;
import trainee.task.university.core.domain.model.LessonModel;
import trainee.task.university.core.domain.service.LessonService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonFacade {

    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    public LessonFacade(LessonService lessonService, LessonMapper lessonMapper) {
        this.lessonService = lessonService;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonDto> findAllLessons() {
        List<LessonModel> lessonModelList = lessonService.findAllLessons();
        return lessonModelList.stream().map(lessonMapper::modelToDto).collect(Collectors.toList());
    }

    public LessonDto findLessonById(Long id) throws NotFoundException {
        return lessonMapper.modelToDto(lessonService.findLessonById(id));
    }

    public LessonDto save(LessonDto lessonDto) {
        LessonModel lessonModel = lessonMapper.dtoToModel(lessonDto);
        return lessonMapper.modelToDto(lessonService.save(lessonModel));
    }

    public LessonDto update(Long id, LessonDto lessonDto) throws NotFoundException {
        LessonModel lessonModel = lessonMapper.dtoToModel(lessonDto);
        return lessonMapper.modelToDto(lessonService.update(id, lessonModel));
    }

    public void delete(Long id) throws NotFoundException {
        lessonService.delete(id);
    }
}
