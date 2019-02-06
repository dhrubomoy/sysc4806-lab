package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @GetMapping("/addressBook")
    public String getAddressBook(Model model) {
        model.addAttribute("addressBook", new AddressBook());
        return "addressBook";
    }

    @PostMapping("/addressBook")
    public String createAddressBook(@ModelAttribute AddressBook addressBook, Model model) {
        AddressBook ab = new AddressBook();
        addressBookRepository.save(ab);
        model.addAttribute("addressBook", ab);
        return "addressBook";
    }

}