package planner.controller;
/**
 * Created by Anna Platash on 4/15/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import planner.entity.TaskData;
import planner.entity.UserData;
import planner.service.TaskService;
import planner.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

@Controller
@RequestMapping("/")
public class PlannerController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
    public String saveUser(@Valid UserData user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registration";
        }
        userService.addUser(user);
        model.addAttribute("success", "User " + user.getName() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = {"/", "/login" }, method = RequestMethod.GET)
    public String showLogin(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "login";
    }

    @RequestMapping(value = {"/", "/login" }, method = RequestMethod.POST)
    public String validateUser(@Valid UserData user, HttpServletRequest request,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        Integer id = userService.validateUser(user);
        if(id != null) {
            user.setId(id);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:calendar";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, ModelMap model) {
        request.getSession().invalidate();
        model.clear();
        return "redirect:calendar";
    }

    @RequestMapping(value = { "/editUser{id}" }, method = RequestMethod.GET)
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
        TaskData taskData = new TaskData();
        model.addAttribute("taskData", taskData);
        model.addAttribute("edit", false);
        return "newTask";
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.POST)
    public String saveTask(@ModelAttribute("taskData") TaskData taskData, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newTask";
        }
        taskService.addTask(taskData);
        model.addAttribute("success", "User " + taskData.getTitle() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = { "/calendar" }, method = RequestMethod.GET)
    public String showCal(HttpServletRequest request, ModelMap model) {
        UserData userData = (UserData)request.getSession().getAttribute("user");
        try {
            if(userData.getId() != null){
                return "calendar";
            } else {
                return "redirect:login";
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
            return "redirect:login";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/cal/json", method = RequestMethod.GET, params = {"start", "end"})
    public String loadTasks(@RequestParam(value = "start") String start,
                            @RequestParam(value = "end") String end,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        UserData user = (UserData) request.getSession().getAttribute("user");
        List<TaskData> taskDataList = new ArrayList<TaskData>();
        try {
            if(user.getId() != null) {
                taskDataList = taskService.getTasksFromIntervalByUserId(start, end, user.getId());

            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        String json = new Gson().toJson(taskDataList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return json;
    }

    @RequestMapping(value = "/tasks_{taskId}", method=RequestMethod.GET)
    public String editTask(@PathVariable Integer taskId, ModelMap model) {
        TaskData taskData = taskService.getTaskById(taskId);
        model.addAttribute("taskData", taskData);
        model.addAttribute("edit", true);
        return "editTask";
    }

    @RequestMapping(value = { "/tasks_{taskId}" }, method = RequestMethod.POST)
    public String updateTask(@PathVariable Integer taskId,
                             @ModelAttribute("taskData") TaskData taskData,
                             BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "editTask";
        }
        taskService.updateTask(taskData);
        model.addAttribute("success", "User " + taskData.getTitle() + " registered successfully");
        return "success";
    }



}
