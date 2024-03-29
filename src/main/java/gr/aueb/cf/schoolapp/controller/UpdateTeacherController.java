package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/schoolapp/update")
public class UpdateTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/school/static/templates/teacherupdate.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		TeacherUpdateDTO newTeacherDTO = new TeacherUpdateDTO();
		newTeacherDTO.setId(id);
		newTeacherDTO.setFirstname(firstname);
		newTeacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", newTeacherDTO);
		
		try {
			Map<String, String > errors = TeacherValidator.validate(newTeacherDTO);

			if (!errors.isEmpty()) {
				String firstnameMessage = (errors.get("firstname") != null) ? "Firstname: " + errors.get("firstname") : "";
				String lastnameMessage = (errors.get("lastname") != null) ? "Lastname: " + errors.get("lastname") : "";
					request.setAttribute("error", firstnameMessage + lastnameMessage);
					request.getRequestDispatcher("/school/static/templates/teacherupdate.jsp")
							.forward(request, response);
				}

				Teacher teacher =  teacherServ.updateTeacher(newTeacherDTO);
				request.setAttribute("message", "");
				request.setAttribute("teacher", teacher);
				request.getRequestDispatcher("/school/static/templates/teacherupdated.jsp")
						.forward(request, response);
			} catch (TeacherNotFoundException | TeacherDAOException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
				request.getRequestDispatcher("/schoolapp/static/templates//teacherupdated.jsp")
						.forward(request, response);

			}
		}
}
