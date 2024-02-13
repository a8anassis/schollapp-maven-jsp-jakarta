package gr.aueb.cf.schoolapp.dao;

public class UserDAOImpl implements IUserDAO {
//    @Override
//    public User getByUsername(String username) {
//        return new User(1, username, "password");
//    }

    @Override
    public boolean isUserValid(String username, String password) {
        return true;
    }
}
