package com.tkosmulski.amore.controllers;

import com.tkosmulski.amore.dto.IdDto;
import com.tkosmulski.amore.dto.LoginDataDto;
import com.tkosmulski.amore.service.PersonLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    PersonLoginService loginService;

    @Autowired
    public LoginController(PersonLoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public IdDto add(LoginDataDto dto){
        int id = loginService.login(dto);
        return new IdDto(id);
    }
}
