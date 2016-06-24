package planner.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import planner.entity.TaskData;
import planner.service.TaskService;
import planner.service.UserService;
import planner.entity.UserData;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Anna Platash on 6/24/16.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {planner.config.PlannerConfig.class})
@WebAppConfiguration
public class PlannerControllerTest {

    public MockMvc mockMvc;
    public TestDataGenerator dataGenerator = new TestDataGenerator();
    public UserData userData1 = dataGenerator.generateUser1();
    public UserData userData2 = dataGenerator.generateUser2();
    public TaskData taskData1 = dataGenerator.generateTask1();
    public TaskData taskData2 = dataGenerator.generateTask2();

    private static final String VIEW_RESOLVER_PREFIX = "/webapp/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Mock
    public TaskService taskService = Mockito.mock(TaskService.class);
    @Mock
    public UserService userService = Mockito.mock(UserService.class);

    @InjectMocks
    PlannerController controller = new PlannerController();

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        //Mockito.reset(userService);
        //Mockito.reset(taskService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver())
                .build();
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }

    @Test
    public void testNewUser() throws Exception {

    }

    @Test
    public void testSaveUser() throws Exception {

    }

    @Test
    public void testShowLogin() throws Exception {

    }

    @Test
    public void testValidateUser() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {

    }

    @Test
    public void testEditUser() throws Exception {

        when(userService.getUserById(1)).thenReturn(userData1);
        mockMvc.perform(get("/editUser"))
                .andExpect(status().isFound());

    }

    @Test
    public void testShowAllUsers() throws Exception {
        List<UserData> users = new ArrayList<UserData>();
        users.add(userData1);
        users.add(userData2);
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewTask() throws Exception {

    }

    @Test
    public void testSaveTask() throws Exception {

    }

    @Test
    public void testShowCal() throws Exception {

    }

    @Test
    public void testLoadTasks() throws Exception {

    }

    @Test
    public void testEditTask() throws Exception {

    }

    @Test
    public void testUpdateTask() throws Exception {

    }
}