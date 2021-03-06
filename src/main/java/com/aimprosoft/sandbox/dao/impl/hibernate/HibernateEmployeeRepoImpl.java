package com.aimprosoft.sandbox.dao.impl.hibernate;

import com.aimprosoft.sandbox.dao.EmployeeRepo;
import com.aimprosoft.sandbox.domain.Employee;
import com.aimprosoft.sandbox.exception.DatabaseException;
import com.aimprosoft.sandbox.util.database.HibernateUtil;
import com.aimprosoft.sandbox.util.database.StatementsHQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaLiK on 29.03.19
 */
@Repository
public class HibernateEmployeeRepoImpl implements EmployeeRepo {
    private static Logger LOG = LogManager.getLogger(HibernateEmployeeRepoImpl.class);

    public HibernateEmployeeRepoImpl() {
    }

    @Override
    public ArrayList<Employee> getAllByDepartmentId(Long id) throws DatabaseException {
        ArrayList<Employee> employees;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employees = new ArrayList<>(session
                    .createQuery(StatementsHQL.GET_ALL_EMPLOYEES, Employee.class)
                    .setParameter("department_id", id)
                    .getResultList());
        } catch (Exception e) {
            LOG.error("Can not get Employees by Department ID\n{}", e.getMessage());
            throw new DatabaseException("Fail to get employees!");
        }
        return employees;
    }

    @Override
    public boolean checkEmployee(Employee employee) throws DatabaseException {
        List<Employee> employees;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query q = session.createQuery(StatementsHQL.CHECK_EMPLOYEE)
                    .setParameter("employee_id", employee.getID())
                    .setParameter("employee_email", employee.getEmail());

            employees = q.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not get Employees by Email\n{}", e.getMessage());
            throw new DatabaseException("Fail to check employee!");
        }

        return employees.isEmpty();
    }

    @Override
    public void createEmployee(Employee employee) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not create Employee\n{}", e.getMessage());
            throw new DatabaseException("Fail to add new employee!");
        }
    }

    @Override
    public void deleteEmployeeById(Long id) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query q = session.createQuery(StatementsHQL.DELETE_EMPLOYEE)
                    .setParameter("employee_id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not delete Employee {}\n{}", id, e.getMessage());
            throw new DatabaseException("Fail to delete employee!");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws DatabaseException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Can not update Employee\n{}", e.getMessage());
            throw new DatabaseException("Fail to update employee!");
        }
    }
}
