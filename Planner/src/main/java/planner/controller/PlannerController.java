package planner.controller;
/**
 * Created by Anna Platash on 4/15/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import planner.entity.TaskData;
import planner.entity.UserData;
import planner.service.TaskService;
import planner.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class PlannerController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public String saveUser(@Valid UserData user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.addUser(user);
        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }


    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String id, ModelMap model) {
        UserData user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET)
    public String showAllUsers(ModelMap model) {
        List users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = { "/tasks" }, method = RequestMethod.GET)
    public String showAllTasks(ModelMap model) {
        List tasks = taskService.getAllTasks();

        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.GET)
    public String newTask(ModelMap model) {
        TaskData task = new TaskData();
        model.addAttribute("task", task);
        model.addAttribute("edit", false);
        return "newTask";
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.POST)
    public String saveTask(@ModelAttribute("task") TaskData task, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newTask";
        }
        taskService.addTask(task);
        model.addAttribute("success", "User " + task.getId() + " registered successfully");
        return "success";
    }

}
