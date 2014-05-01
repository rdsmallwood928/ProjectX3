package com.smallwood.projectx.helpers;

import com.smallwood.projectx.ButtonMethod;
import com.smallwood.projectx.Professional;
import com.smallwood.projectx.ProfessionalCenter;
import com.smallwood.projectx.RegistryCenter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by bigwood928 on 4/14/14.
 */
public class ControllerHelper extends HelperBase {

    private String professionals;

    public ControllerHelper(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        super(request, response, servlet);
    }

    public void doGet()  {
        try {
            executeButtonMethod();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ButtonMethod(buttonName="search", isDefault=false)
    protected void searchForProfessionals() {
        try {
            String address = "index.jsp";
            if (request.getParameter("search") != null) {
                address = "results.jsp";
                String search = request.getParameter("search");
                StringBuilder builder = new StringBuilder("<table>");
                ProfessionalCenter center = RegistryCenter.get(ProfessionalCenter.class);
                Collection<Professional> allProfessionals = center.getProfessionals();
                for (Professional professional : allProfessionals) {
                    if (professional.getSkills().contains(search)) {
                        builder.append("<tr><td>")
                                .append(professional.getFirstName()).append(" ").append(professional.getLastName())
                                .append("</td><td>")
                                .append(professional.getSkills().toString())
                                .append("</td><td>")
                                .append("<form action=controller method=post>")
                                .append("<input type=\"hidden\" name=\"id\" value=\"" + professional.getId() + "\">")
                                .append("<input type=\"submit\" name=\"emailcontact\" value=\"email\">")
                                .append("</form>");
                    }
                }
                builder.append("</table>");
                professionals = builder.toString();
                request.setAttribute("results", this);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }catch (Exception e) {
            System.out.println("Something bad....");
        }
    }

    @ButtonMethod(buttonName = "createaccount", isDefault = false)
    protected void createAccount() {
        try {
            String address = "confirm.jsp";
            Professional professional = new Professional();
            boolean success = true;
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("email");
            String skills = request.getParameter("skills");
            if (firstName != null) {
                professional.setFirstName(firstName);
            }
            if (lastName != null) {
                professional.setLastName(lastName);
            }
            if (email != null) {
                professional.setEmail(email);
            }
            if (skills != null) {
                String[] skillsArray =  skills.split(",");
                HashSet<String> skillsSet = new HashSet<String>();
                for(String skill : skillsArray) {
                    skillsSet.add(skill);
                }
                professional.setSkills(skillsSet);
            }
            ProfessionalCenter center = RegistryCenter.get(ProfessionalCenter.class);
            center.addProfessional(professional);
            request.setAttribute("account", professional);
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Something bad happened " + Utilities.exceptionToString(e));
        }
    }

    @ButtonMethod(buttonName = "emailcontact", isDefault = false)
    protected void sendEmail() {
        ProfessionalCenter professionalCenter = RegistryCenter.get(ProfessionalCenter.class);
        Long id = Long.parseLong(request.getParameter("id"));
        Professional professional = professionalCenter.getProfessionalById(id);

        String to = professional.getEmail();
        // Sender's email ID needs to be mentioned
        String from = "rdsmallwood928@gmail.com";
        // Assuming you are sending email from localhost
        String host = "localhost";
        final String username = "rdsmallwood928";
        final String password = "j0yandb3rt";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Projet X up and running!");
            message.setText("Hello!  Somebody on ProjectX.com has selected you from out vast database of candidates!  If you are interested, click the interested button below " +
                    "and we will let them know!  **Hey Brian, I generated this message automagically from Projectx, pretty neat eh?  Well I'll get back to the code mines TTYL!!**");

            Transport.send(message);
        }catch (MessagingException mex) {
            System.out.println(Utilities.exceptionToString(mex));
        }
    }

    @ButtonMethod(buttonName="login", isDefault = false)
    protected void login() {
        fillBeanFromRequest(loggedInAccount);

    }

    public String getProfessionals() {
        return professionals;
    }

    public void setProfessionals(String professionals) {
        this.professionals = professionals;
    }
}
