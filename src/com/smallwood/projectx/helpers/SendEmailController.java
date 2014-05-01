package com.smallwood.projectx.helpers;

import com.smallwood.projectx.Controller;
import com.smallwood.projectx.Professional;
import com.smallwood.projectx.ProfessionalCenter;
import com.smallwood.projectx.RegistryCenter;;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Created by bigwood928 on 4/20/14.
 */
public class SendEmailController extends HelperBase {

    public SendEmailController(HttpServletRequest request, HttpServletResponse response, Controller controller) {
        super(request, response, controller);
    }

    @Override
    public void doGet() {

    }
}
