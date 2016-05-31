package planner.controller;
/**
 * Created by Anna Platash on 4/15/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import planner.entity.TaskData;
import planner.entity.UserData;
import planner.formEntity.CalendarData;
import planner.formEntity.NewTaskForm;
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
        NewTaskForm taskForm = new NewTaskForm();
        model.addAttribute("taskForm", taskForm);
        model.addAttribute("edit", false);
        return "newTask";
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.POST)
    public String saveTask(@ModelAttribute("taskForm") NewTaskForm taskForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newTask";
        }
        taskService.addTask(taskForm);
        model.addAttribute("success", "User " + taskForm.getName() + " registered successfully");
        return "success";
    }

    @RequestMapping(value = { "/cal" }, method = RequestMethod.GET)
    public String showCal(ModelMap model) {
        NewTaskForm taskForm = new NewTaskForm();
        model.addAttribute("taskForm", taskForm);
        model.addAttribute("edit", false);
        return "calendar";
    }

    @ResponseBody
    @RequestMapping(value = "/cal/json", method = RequestMethod.GET, params = {"start", "end"})

    public String loadTasks(@RequestParam(value = "start") String start,
                            @RequestParam(value = "end") String end,
                            HttpServletResponse response) throws Exception {

        System.out.println(start);
        System.out.println(end);

        List<TaskData> taskDataList = taskService.getTasksFromInterval(start, end);
        List<CalendarData> calendarDatas = new ArrayList<CalendarData>();
        for(TaskData taskData: taskDataList) {
            calendarDatas.add(new CalendarData(taskData));
        }
        String json = new Gson().toJson(calendarDatas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return json;
    }

    @RequestMapping(value = "/cal/saveTask", method = RequestMethod.POST)
    @ResponseBody
    public void saveTask(@RequestBody String jsonString, Principal principal) throws Exception {
        Gson gson = new Gson();
        NewTaskForm taskData = gson.fromJson(jsonString, NewTaskForm.class);
        taskService.addTask(taskData);

    }



}
