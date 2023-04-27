package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository; 

    @Transactional
    public int upsertTask(List<Task> taskList, String username) {
        Optional<User> opt = userRepository.findUserByUsername(username); 
        User taskUser; 
        if(opt.isEmpty()) {
            taskUser = new User(); 
            taskUser.setUsername(username);
            taskUser.setName(username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase());
            taskUser.setUserId(userRepository.insertUser(taskUser));
        } else {
            taskUser = opt.get(); 
        }

        int taskCount = 0;
        for (Task t : taskList) {
            t.setUser_id(taskUser.getUserId());
            taskRepository.insertTask(t); 
            taskCount++; 
        }
        return taskCount; 
    }
}

/* 
public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(FIND_USER_BY_USERNAME, username);
        if (rs.first()) {
            return Optional.of(User.populate(rs));
        } 
        return Optional.empty(); 
    }
public String insertUser(User user) {
    String newUserId = UUID.randomUUID().toString().substring(0, 8); 
    int rowsAffected = template.update(FIND_USER_BY_USERNAME, newUserId, user.getUsername(), user.getName());
    if (rowsAffected > 0) {
        return newUserId; 
    }
    return "Inser user error"; 
}

public boolean insertTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); 
        int rowCount = jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, Date.valueOf(task.getDue_date()));
            return ps; 
        }, keyHolder); 
        BigInteger primaryKey = (BigInteger) keyHolder.getKey(); 
        task.setTask_id(primaryKey.intValue());
        return rowCount > 0;         
    }
*/