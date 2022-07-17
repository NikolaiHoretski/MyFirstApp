package com.horetski.example.controller;

import com.horetski.example.domain.Ordering;
import com.horetski.example.domain.User;
import com.horetski.example.repo.OrderingRepo;
import com.horetski.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ReservationController {
    @Autowired
    private OrderingRepo orderingRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/reservation")
    public String getAllReservation(final Model model, Authentication authentication) {
        User user = userRepo.findByUsername(authentication.getName());
        Iterable<Ordering> orderings = orderingRepo.findAllByUser(user);
        model.addAttribute("ordering", orderings);
        return "reservation";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteOrdering(@RequestParam(value = "id") long id, final Model model) {
        Ordering ordering = orderingRepo.findById(id).orElseThrow();
        orderingRepo.deleteById(id);
        model.addAttribute("ordering", ordering);
        return "redirect:/reservation";
    }
}






