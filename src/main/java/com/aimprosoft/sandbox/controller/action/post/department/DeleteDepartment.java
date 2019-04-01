package com.aimprosoft.sandbox.controller.action.post.department;

import com.aimprosoft.sandbox.controller.action.Action;
import com.aimprosoft.sandbox.util.service.DatabaseService;
import com.aimprosoft.sandbox.util.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author BaLiK on 26.03.19
 */
public class DeleteDepartment implements Action {
    private static Logger LOG = Logger.getLogger(DeleteDepartment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (Validator.validateId(id)) {
            try {
                DatabaseService.getInstance().getDepartmentService().deleteDepartmentById(id);
            } catch (SQLException e) {
                request.setAttribute("dbError", true);
                request.setAttribute("errorMessage", "Fail to delete department!");
            }
            LOG.info("Department " + id + " was removed!");
            response.sendRedirect("/");
        } else {
            response.sendRedirect("?action-get=default&flag=" + id);
        }
    }
}
