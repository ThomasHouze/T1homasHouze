package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    BHRepository bhRepository;

    @RequestMapping("/")
    public String listBH(Model model) {
        model.addAttribute("courses", bhRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String BHForm(Model model) {
        model.addAttribute("course", new BH());
        return "BHForm";

    }

    @PostMapping("/process")
    public String processForm(@Valid BH bh, BindingResult result) {
        if (result.hasErrors()) {
            return "JobForm";
        }
        bhRepository.save(bh);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showBH(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("course", bhRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateBH (@PathVariable("id") long id,
                                Model model){

        model.addAttribute("course", bhRepository.findById(id).get());
        return "BHform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        bhRepository.deleteById(id);
        return "redirect:/";
    }
}


