package com.controllers;

import com.models.City;
import com.models.Country;
import com.services.ICityService;
import com.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    @GetMapping("/")
    public String cityList(Model model) {
        Iterable<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "/city/list";
    }

    @GetMapping("/create")
    public String createCity(Model model) {
        model.addAttribute("city", new City());
        return "city/create";
    }

    @PostMapping("/create")
    public String saveNewStudent(@Validated @ModelAttribute("city") City city, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes) {
//        new City().validate(city, bindingResult);
        if (!bindingResult.hasFieldErrors()) {
            cityService.save(city);
            redirectAttributes.addFlashAttribute("message", "Added new city");
            return "redirect:/";
        }
        return "city/create";
    }

    @GetMapping("/edit/{id}")
    public String editCity(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        return "city/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditCity(@Validated City city,BindingResult bindingResult, Model model,
                               RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors()) {
            cityService.save(city);
            redirectAttributes.addFlashAttribute("message", "Updated city");
            return "redirect:/";
        }
//        City newCity = cityService.findById(city.getId());
        model.addAttribute("city",city);
        return "city/edit";
//        redirectAttributes.addFlashAttribute(city);
//        return "redirect:/edit/"+city.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        return "city/delete";
    }

    @PostMapping("/delete")
    public String saveDeleteCity(@ModelAttribute("city") City city, RedirectAttributes redirectAttributes) {
            cityService.delete(city.getId());
            redirectAttributes.addFlashAttribute("message"," Deleted City");
            return "redirect:/"





    @GetMapping("/view/{id}")
    public String getCityDetail(@PathVariable("id") Long id, Model model) {
        City city = cityService.findById(id);
        if (city != null) {
            model.addAttribute("city", city);
            return "/city/view";
        } else {
            return "error.404";
        }
    }
}
