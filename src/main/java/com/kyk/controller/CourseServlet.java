package com.kyk.controller;

import com.kyk.domain.Course;
import com.kyk.model.CourseModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
        "/",
        "/add-course",
        "/courses",
        "/course-list"
})
public class CourseServlet extends AbstractBeanFactoryServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var page = switch (req.getServletPath()) {
            case "/" -> {
                var model = getBean("courseModel", CourseModel.class);
                req.setAttribute("courses", model.getAll());
                yield "/index.jsp";
            }
            case "/course-list" -> {
                var model = getBean("courseModel", CourseModel.class);
                req.setAttribute("courses", model.getAll());
                yield "/course-list.jsp";
            }
            case "/add-course" -> {
                yield "/add-course.jsp";
            }
            default -> {
                yield "/course-list.jsp";
            }
        };

        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var name = req.getParameter("name");
        var duration = req.getParameter("duration");
        var fees = req.getParameter("fees");
        var description = req.getParameter("description");

        var course = new Course();
        course.setName(name);
        course.setDuration(Integer.parseInt(duration));
        course.setFees(Integer.parseInt(fees));
        course.setDescription(description);

        var courseModel = getBean("courseModel", CourseModel.class);
        courseModel.save(course);

        resp.sendRedirect("course-list");

    }
}
