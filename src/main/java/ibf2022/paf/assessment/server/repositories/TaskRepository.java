package ibf2022.paf.assessment.server.repositories;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;
import static ibf2022.paf.assessment.server.repositories.DBQueries.*;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // if task is inserted successful, will return true
    public boolean insertTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); 
        int rowCount = jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, task.getDescription());
            ps.setInt(2, task.getPriority());
            ps.setDate(3, Date.valueOf(task.getDue_date()));
            ps.setString(4, task.getUser_id());
            return ps; 
        }, keyHolder); 
        BigInteger primaryKey = (BigInteger) keyHolder.getKey(); 
        task.setTask_id(primaryKey.intValue());
        return rowCount > 0;         
    }
}