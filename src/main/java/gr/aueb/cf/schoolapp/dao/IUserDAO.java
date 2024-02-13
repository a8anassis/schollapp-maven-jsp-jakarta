package gr.aueb.cf.schoolapp.dao;

//import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
//import gr.aueb.cf.schoolapp.model.User;

//import java.util.List;

public interface IUserDAO {
//    User insert(User user) throws UserDAOException;
//    User update(User user) throws UserDAOException;
//    void delete(int id) throws UserDAOException;
//    List<User> getAll();
//    User getByUsername(String username); // throws UserDAOException;
    boolean isUserValid(String username, String password);
}
