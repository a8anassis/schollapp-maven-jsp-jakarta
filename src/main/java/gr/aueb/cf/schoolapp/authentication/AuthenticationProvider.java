package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dto.UserLoginDTO;

public class AuthenticationProvider {

    private static final IUserDAO userDAO = new UserDAOImpl();

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getUsername().equals("test@aueb.gr")) {
            return false;
        }
        return userDAO.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}
