package com.cuong.bds.controller;

import com.cuong.bds.entity.HouseEntity;
import com.cuong.bds.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    public String listHouses(Model model) {
        List<HouseEntity> houses = houseService.getAllHouses();
        model.addAttribute("houses", houses);
        return "house-list";
    }

    @GetMapping("/new")
    public String newHouseForm(Model model) {
        model.addAttribute("house", new HouseEntity());
        return "new-house";
    }

    @PostMapping
    public String saveHouse(@ModelAttribute("house") HouseEntity house) {
        houseService.saveHouse(house);
        return "redirect:/houses";
    }
}
