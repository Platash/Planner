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
            return "redirect:login";
        }
        UserData userValidated = userService.validateUser(user);
        if(userValidated != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userValidated);
            return "redirect:calendar";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, ModelMap model) {
        request.getSession().invalidate();
        model.clear();
        return "redirect:login";
    }

    @RequestMapping(value = { "/editUser" }, method = RequestMethod.GET)
    public String editUser(HttpServletRequest request, ModelMap model) {
        if(checkPermission(request)) {
            UserData user = (UserData)request.getSession().getAttribute("user");
            model.addAttribute("user", user);
            return "editUser";
        } else {
            return "redirect:calendar";
        }
    }

    @RequestMapping(value = { "/editUser" }, method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") UserData userDataNew,
                             HttpServletRequest request, ModelMap model, BindingResult result) {
        if (result.hasErrors() || !checkPermission(request)) {
            return "editUser";
        }
        UserData currentUser = (UserData)request.getSession().getAttribute("user");
        if(request.getParameter("delete") != null) {
            int resultCount = userService.deleteUserById(currentUser.getId());
            if(resultCount == 1) {
                request.getSession().invalidate();
                model.clear();
                return "redirect:logout";
            }
        }
        if(request.getParameter("update") != null) {
            int resultCount = userService.updateUser(currentUser, userDataNew);
            if(resultCount == 1) {
                currentUser.setName(userDataNew.getName());
                currentUser.setPassword(userDataNew.getPassword());
                HttpSession session = request.getSession();
                session.setAttribute("user", currentUser);
                model.addAttribute("success", "User " + userDataNew.getLogin() + " updated successfully");
                return "success";
            }
        }
        return "editUser";
    }



    @RequestMapping(value = { "/users" }, method = RequestMethod.GET)
    public String showAllUsers(ModelMap model) {
        List users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.GET)
    public String newTask(ModelMap model, HttpServletRequest request) {
        if(checkPermission(request)) {
            TaskData taskData = new TaskData();
            model.addAttribute("taskData", taskData);
            model.addAttribute("edit", false);
            return "newTask";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = { "/newTask" }, method = RequestMethod.POST)
    public String saveTask(@ModelAttribute("taskData") TaskData taskData,  HttpServletRequest request,
                           BindingResult result, ModelMap model) {
        if (result.hasErrors() || !checkPermission(request)) {
            return "redirect:newTask";
        }
        int userId = ((UserData)request.getSession().getAttribute("user")).getId();
        taskService.addTask(taskData, userId);
        model.addAttribute("success", "Task " + taskData.getTitle() + " created successfully");
        return "success";
    }

    @RequestMapping(value = { "/calendar" }, method = RequestMethod.GET)
    public String showCal(HttpServletRequest request) {
        if(checkPermission(request)){
            return "calendar";
        } else {
            return "redirect:login";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/cal/json", method = RequestMethod.GET, params = {"start", "end"})
    public String loadTasks(@RequestParam(value = "start") String start,
                            @RequestParam(value = "end") String end,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        if(checkPermission(request)){

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
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = "/tasks_{taskId}", method=RequestMethod.GET)
    public String editTask(@PathVariable Integer taskId, HttpServletRequest request, ModelMap model) {
        if(checkPermission(request)){
            TaskData taskData = taskService.getTaskById(taskId);
            if(taskData == null) {
                return "redirect:calendar";
            }
            UserData userData = (UserData)request.getSession().getAttribute("user");
            if(userService.checkUserTaskPermission(userData.getId(), taskId)) {
                model.addAttribute("taskData", taskData);
                model.addAttribute("edit", true);
                return "editTask";
            } else {
                return "redirect:calendar";
            }
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(value = { "/tasks_{taskId}" }, method = RequestMethod.POST)
    public String updateTask(@PathVariable Integer taskId,
                             @ModelAttribute("taskData") TaskData taskData,
                             BindingResult result, ModelMap model, HttpServletRequest request) {
        if (result.hasErrors() || !checkPermission(request)) {
            return "editTask";
        }
        if(request.getParameter("delete") != null) {
            if(taskData.getId() == null) {
                taskData.setId(taskId);
            }
            taskService.deleteTaskById(taskId);
            model.addAttribute("success", "User " + taskData.getTitle() + " was deleted");
            return "success";
        }
        if(request.getParameter("update") != null) {
            if(taskData.getId() == null) {
                taskData.setId(taskId);
            }
            taskService.updateTask(taskData);
            model.addAttribute("success", "User " + taskData.getTitle() + " updated successfully");
            return "success";
        }
        return "editTask";
    }

    private boolean checkPermission(HttpServletRequest request) {
        UserData userData = (UserData)request.getSession().getAttribute("user");
        try {
            if(userData.getId() != null){
                return true;
            } else {
                return false;
            }
        } catch(NullPointerException e) {
            //e.printStackTrace();
            return false;
        }
    }

}
