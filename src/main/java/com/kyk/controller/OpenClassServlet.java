package com.kyk.controller;

import com.kyk.domain.Course;
import com.kyk.domain.OpenClass;
import com.kyk.model.CourseModel;
import com.kyk.model.OpenClassModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {
        "/classes",
        "/class-edit"
})
public class OpenClassServlet extends AbstractBeanFactoryServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var courseId = req.getParameter("courseId");
        var courseModel = getBean("courseModel", CourseModel.class);
        var course = courseModel.findById(Integer.parseInt(courseId));
        req.setAttribute("course", course);

        var page = switch (req.getServletPath()) {
            case "/classes" -> {
                var model = getBean("openClassModel", OpenClassModel.class);
                req.setAttribute("classes", model.findByCourse(Integer.parseInt(courseId)));
                yield "/classes.jsp";
            }
            default -> {
                yield "/class-edit.jsp";
            }
        };
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var courseId = req.getParameter("courseId");
        var startDate = req.getParameter("sd");
        var teacher = req.getParameter("teacher");

        var courseModel = getBean("courseModel", CourseModel.class);
        var byCourse = courseModel.findById(Integer.parseInt(courseId));
        System.out.println("byCourse = " + byCourse);

        var oc = new OpenClass();
        oc.setCourse(byCourse);
        oc.setStartDate(LocalDate.parse(startDate));
        oc.setTeacher(teacher);

        var model = getBean("openClassModel", OpenClassModel.class);
        model.create(oc);

        resp.sendRedirect("classes?courseId=%s".formatted(courseId));

    }
}
