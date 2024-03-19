package com.JYProject.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @Autowired

    @GetMapping("/")
    public String list(){
        return "board/list.html";
    }

}
