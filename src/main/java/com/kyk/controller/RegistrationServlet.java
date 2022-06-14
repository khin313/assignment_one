package com.kyk.controller;

import com.kyk.domain.Registration;
import com.kyk.model.OpenClassModel;
import com.kyk.model.RegistrationModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
        "/regs",
        "/reg-edit"
})
public class RegistrationServlet extends AbstractBeanFactoryServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var openClassId = req.getParameter("ocId");
        var ocModel = getBean("openClassModel", OpenClassModel.class);
        var openClass = ocModel.findById(Integer.parseInt(openClassId));

        req.setAttribute("ocourse", openClass);

        var page = switch (req.getServletPath()) {
          case "/regs" -> {
              var model = getBean("registrationModel", RegistrationModel.class);
              req.setAttribute("regs", model.findByClassModel(Integer.parseInt(openClassId)));
              yield "/registration.jsp";
          }
            default -> "/reg-edit.jsp";
        };

        getServletContext().getRequestDispatcher(page).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var oclassId = req.getParameter("ocId");
        var studentName = req.getParameter("sn");
        var phone = req.getParameter("ph");
        var email = req.getParameter("email");

        var ocModel = getBean("openClassModel", OpenClassModel.class);
        var openClass = ocModel.findById(Integer.parseInt(oclassId));

        System.out.println("openClass = " + openClass);

        var reg = new Registration();
        reg.setOpenClass(openClass);
        reg.setStudent(studentName);
        reg.setPhone(phone);
        reg.setEmail(email);

        var model = getBean("registrationModel", RegistrationModel.class);
        model.create(reg);

        resp.sendRedirect("regs?ocId=%s".formatted(oclassId));
    }
}
