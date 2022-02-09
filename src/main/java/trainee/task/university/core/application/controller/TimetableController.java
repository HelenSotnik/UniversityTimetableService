package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.core.application.dto.TimetableDto;
import trainee.task.university.core.application.facade.TimetableFacade;
import trainee.task.university.core.database.entity.DayOfTheWeek;

import javax.validation.Valid;
import java.util.List;

@Api("Timetable Controller")
@RequestMapping("/timetables")
@RestController
public class TimetableController {

    @Autowired
    private TimetableFacade timetableFacade;

    @ApiOperation("Find All Timetable, returns list of timetables from Database")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableDto> findAll() {
        return timetableFacade.findAllTimetables();
    }

    @ApiOperation("Find Timetable by its Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimetableDto findTimetableById(@PathVariable Long id) throws NotFoundException {
        return timetableFacade.findTimetableById(id);
    }

    @ApiOperation("Delete timetable found by Timetable Id from Database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        timetableFacade.delete(id);
    }

    @ApiOperation("Save timetable to Database")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimetableDto save(@RequestBody @Valid TimetableDto timetableDto) {
        return timetableFacade.save(timetableDto);
    }

    @ApiOperation("Update  timetable found by Timetable Id In Database")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TimetableDto update(@PathVariable Long id, @RequestBody @Valid TimetableDto timetableDto)
            throws NotFoundException {
        return timetableFacade.update(id, timetableDto);
    }

    @ApiOperation("Find timetable by Student GroupName and Day of The Week")
    @GetMapping("/{groupName}/{day}")
    @ResponseStatus(HttpStatus.OK)
    public TimetableDto findTimetableByGroupNameAndDayOfTheWeek(@PathVariable String groupName, @PathVariable DayOfTheWeek day)
            throws NotFoundException {
        return timetableFacade.findByStudentGroupNameAndDayOfTheWeek(groupName, day);
    }
}
