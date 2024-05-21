package org.example.b9routeridemanager.controllers;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.b9routeridemanager.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final Gson gson;


    @GetMapping("/")
    public String startPage(Model model, Authentication authentication) {
        model.addAttribute("user", gson.toJson(authentication.getPrincipal()));
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "from") String from,
                         @RequestParam(name = "to") String to,
                         @RequestParam(name = "departure_date") String departure_date,
                         @RequestParam(name = "baggage") Integer baggage,
                         Model model, Authentication authentication) {
        model.addAttribute("user", gson.toJson(authentication.getPrincipal()));
        return "search";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/management")
    public String management(Model model, Authentication authentication) {
        model.addAttribute("user", gson.toJson(authentication.getPrincipal()));
        return "management";
    }
    @GetMapping("/payment")
    public String payment(@RequestParam(name = "route_id") String routeId,
                          @RequestParam(name = "price") String price,
                          Model model, Authentication authentication) {
        model.addAttribute("user", gson.toJson(authentication.getPrincipal()));
        return "payment";
    }
    @GetMapping("/my-tickets")
    public String myTickets(Model model, Authentication authentication) {
        model.addAttribute("user", gson.toJson(authentication.getPrincipal()));
        return "myTickets";
    }
}
