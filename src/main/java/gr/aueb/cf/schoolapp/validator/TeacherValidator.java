package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {
    private static final Map<String, String> errors = new HashMap<>();
    private TeacherValidator() {}

    public static Map<String, String> validate(TeacherInsertDTO dto) {

        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }

        return errors;
    }

    public static Map<String, String> validate(TeacherUpdateDTO dto) {
        Map<String, String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }

        return errors;
    }
}
