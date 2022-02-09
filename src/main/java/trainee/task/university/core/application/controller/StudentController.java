package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.application.dto.StudentDto;
import trainee.task.university.core.application.facade.StudentFacade;

import javax.validation.Valid;
import java.util.List;


@Api("Students Controller")
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentFacade studentFacade;

    @ApiOperation("Find All Students method, returns list of all students")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> findAllStudents() {
        return studentFacade.findAllStudents();
    }

    @ApiOperation("Find Student by Id, return student info")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto findStudentById(@PathVariable Long id) throws NotFoundException {
        return studentFacade.findStudentById(id);
    }

    @ApiOperation("Updates information about student found by Student Id")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto update(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto studentDto) throws NotFoundException {
        return studentFacade.update(id, studentDto);
    }

    @ApiOperation("Find student by email")
    @GetMapping("/{email}")
    public StudentDto findStudentByEmail(@PathVariable String email) throws NotFoundException {
        return studentFacade.findStudentByEmail(email);
    }
}
