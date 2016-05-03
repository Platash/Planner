package planner.controller;
/**
 * Created by Anna Platash on 4/15/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import planner.entity.UserData;
import planner.service.UserService;

@Controller
@RequestMapping("/")
public class PlannerController {

    @Autowired
    UserService service;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveUser(@Valid UserData user, BindingResult result,
                               ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        service.saveUser(user);

        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }


    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String id, ModelMap model) {
        UserData user = service.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }


    @RequestMapping(value = { "/delete-{id}-employee" }, method = RequestMethod.GET)
    public String deleteUserById(@PathVariable String id) {
        service.deleteUserById(id);
        return "redirect:/list";
    }

}
