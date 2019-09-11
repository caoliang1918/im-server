package org.zhongweixian.live.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("")
    public ModelAndView welcome() {
        return new ModelAndView("index.html");
    }


}
