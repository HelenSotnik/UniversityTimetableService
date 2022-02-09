package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.domain.service.StudentService;

import javax.validation.Valid;

@Api("Registration Controller" )
@RestController
public class RegistrationController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("Registration in the system by filling the personal information")
    @PostMapping(value = "/registration")
    public String signUpNewStudent(@RequestBody @Valid AddStudentRequestDto studentDto) throws NotFoundException {
        boolean existing = studentService.existsByEmail(studentDto.getEmail());

        if (existing != false) {
            return "There is already an account registered with current email";
        }
        studentService.singUpNewStudent(studentDto);
        return "You are successfully signed up. ";
    }
}
