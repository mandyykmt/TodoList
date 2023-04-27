package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;
import static ibf2022.paf.assessment.server.repositories.DBQueries.*;

// TODO: Task 3

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template; 

    public Optional<User> findUserByUsername(String username) {
        SqlRowSet rs = template.queryForRowSet(FIND_USER_BY_USERNAME, username);
        if (rs.first()) {
            return Optional.of(User.populate(rs));
        } 
        return Optional.empty(); 
    }

    public String insertUser(User user) {
        String newUserId = UUID.randomUUID().toString().substring(0, 8); 
        int rowsAffected = template.update(INSERT_USER, newUserId, user.getUsername(), user.getName());
        if (rowsAffected > 0) {
            return newUserId; 
        }
        return "Inser user error"; 
    }
}
