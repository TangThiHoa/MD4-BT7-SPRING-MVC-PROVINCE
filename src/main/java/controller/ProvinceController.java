package controller;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProvinceService;

import java.util.List;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;

    @GetMapping("")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("pro",new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/province");

        return modelAndView;

    }

    @GetMapping("edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("pro", provinceService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, Province province) {
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        provinceService.save(province);
        return modelAndView;

    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/delete");
        modelAndView.addObject("pro", provinceService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView remove(Province province){
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        provinceService.remove(province.getId());
        return modelAndView;
    }
}