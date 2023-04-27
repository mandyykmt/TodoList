package ibf2022.paf.assessment.server.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
public class TasksController {

    @Autowired
    TodoService todoService; 

    // username=fred&description-0=Buy%20elephants&priority-0=3&dueDate-0=2023-09-02
    @PostMapping(path="/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView postTask(@RequestBody MultiValueMap<String, String> form) {

        String username = form.getFirst("username");
        List<Task> taskList = new ArrayList<Task>();

        int i = 0; 
        while (form.containsKey("description-" + Integer.toString(i))) {
            Task task = new Task(
                form.getFirst("description-" + Integer.toString(i)), 
                Integer.parseInt(form.getFirst("priority-" + Integer.toString(i))), 
                LocalDate.parse(form.getFirst("dueDate-" + Integer.toString(i))));

            taskList.add(task);
            i++; 
        }

        int taskCount = todoService.upsertTask(taskList, username);

        ModelAndView mav = null;
        
        try {
            mav = new ModelAndView("result"); 
            mav.addObject("taskCount", taskCount); 
            mav.addObject("username", username);
            mav.setStatus(HttpStatus.OK);
        } catch(Exception e) {
            mav = new ModelAndView("error");
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return mav; 
    }
}