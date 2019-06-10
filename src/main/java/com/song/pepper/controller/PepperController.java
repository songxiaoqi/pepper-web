package com.song.pepper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pepper")
public class PepperController {


    @RequestMapping(value="/vue",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView vueTest(){
        return new ModelAndView("pepper");
    }
}
