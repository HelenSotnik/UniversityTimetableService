package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.config.security.JwtUtil;
import trainee.task.university.core.application.dto.LoginRequestDto;
import trainee.task.university.core.application.dto.LoginResponseDto;

import javax.validation.Valid;

@Api("Login(Authorization) Controller")
@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @ApiOperation("Login by filling the email and password fields")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception {
        authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return new LoginResponseDto(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Wrong credentials", e);
        }
    }
}
