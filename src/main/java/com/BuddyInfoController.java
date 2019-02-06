package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class BuddyInfoController {
    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @GetMapping("/buddyInfo")
    public String getBuddyInfo(Model model) {
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "buddyInfo";
    }

    @PostMapping("/buddyInfo")
    public String createBuddyInfo(@ModelAttribute BuddyInfo buddyInfo) {
//        return addressBookRepository.save(ab);
        return "result";
    }
}
