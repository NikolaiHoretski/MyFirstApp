package com.horetski.example.controller;


import com.horetski.example.domain.Ordering;
import com.horetski.example.domain.User;
import com.horetski.example.repo.OrderingRepo;
import com.horetski.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderingRepo orderingRepo;

    @GetMapping("/main")
    public String main(Authentication authentication, final Model model) {
        User user = userRepo.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        model.addAttribute("minDate", dateFormat.format(currentDate));
        Calendar maxDate = Calendar.getInstance();
        maxDate.setTime(currentDate);
        maxDate.add(Calendar.DATE, 7);
        model.addAttribute("maxDate", dateFormat.format(maxDate.getTime()));
        return "main";
    }

    @PostMapping("/main")
    public String addOrdering(Authentication authentication, @RequestParam(name = "calendar") String calendar, @RequestParam(name = "time") String time, final Model model) {
        User user = userRepo.findByUsername(authentication.getName());
        Ordering ordering = new Ordering(calendar, time, user);
        orderingRepo.save(ordering);
        return "redirect:/main";
    }
}


