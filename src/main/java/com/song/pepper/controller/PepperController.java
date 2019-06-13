package com.song.pepper.controller;

import com.song.pepper.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pepper")
public class PepperController {


    @RequestMapping(value="/vue",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView vueTest(){
        return new ModelAndView("pepper");
    }

    @RequestMapping(value="/card",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object card(String card){
        card = StringUtils.trim(card);
        if(!StringUtils.isEmpty(card)&&card.length()==17&&StringUtils.isInteger(card)){
            return card+verifyId(card);
        }
        return 1;
    }

    public String verifyId(String id) {
        int count = 0;
        char[] charArr = id.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            int n = Integer.parseInt(charArr[i] + "");
            count += n * (Math.pow(2, 17 - i) % 11);
        }
        switch (count % 11) {
            case 0: return "1";
            case 1: return "0";
            case 2: return "X";
            default:
                return 12 - (count % 11) + "";
        }

    }
}
