package com.aimprosoft.sandbox.controller.action.post.employee;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.dao.impl.DepartmentDAO;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.dao.impl.EmployeeDAO;
import com.aimprosoft.sandbox.util.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BaLiK on 27.03.19
 */
public class EditEmployee implements Action {
    private static Logger LOG = Logger.getLogger(EditEmployee.class);
    private final static String URL = "?action-get=employees&department_id=%d";
    private final static String FAIL_URL = "?action-get=employees&department_id=%d&login=%s&email=%s&rank=%d&date=%s&flag=%s";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) throws IOException {
        String userId = request.getParameter("id");
        String newLogin = request.getParameter("new login");
        String newEmail = request.getParameter("new email");
        String newRank = request.getParameter("new rank");
        String newDate = request.getParameter("new date");
        String departmentId = request.getParameter("department_id");

        if (Validator.validateUser(newLogin, newEmail, newRank, newDate) && Validator.validateId(userId) && Validator.validateId(departmentId)) {
            Employee employee = new Employee(Long.parseLong(userId));
            employee.setLogin(newLogin);
            employee.setEmail(newEmail);
            employee.setRank(Integer.parseInt(newRank));
            employee.setRegistrationDate(newDate);
            employee.setDepartmentID(Long.parseLong(departmentId));

            if (employeeDAO.checkEmployee(employee)) {
                employeeDAO.updateEmployee(employee);
                LOG.info("Employee " + newLogin + " was updated!");
                response.sendRedirect(String.format(URL, Long.parseLong(departmentId)));
            } else {
                response.sendRedirect(String.format(FAIL_URL, Long.parseLong(departmentId), newLogin, newEmail, Integer.parseInt(newRank), newDate, userId));
            }

        } else {
            response.sendRedirect("?action-get=error");
        }
    }
}