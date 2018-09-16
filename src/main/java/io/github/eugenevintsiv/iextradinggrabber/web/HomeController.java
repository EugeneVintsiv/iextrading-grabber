package io.github.eugenevintsiv.iextradinggrabber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Controller to redirect from root to swagger doc page.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}