package com.smallwood.projectx;

import com.smallwood.projectx.helpers.HibernateHelper;
import com.smallwood.projectx.helpers.Utilities;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by bigwood928 on 4/18/14.
 */
public class Initializer extends HttpServlet {


    public Initializer() {
        super();
        RegistryCenter.register(HibernateHelper.class, new HibernateHelper());
        RegistryCenter.register(ProfessionalCenter.class, new ProfessionalCenter());
    }

    @Override
    public void init() throws ServletException {
        String initPath = getInitParameter("logPath");
        String logPath = "/WEB-INF/logs/projectx.log";
        if(initPath != null) logPath = initPath;
        HibernateHelper hibernateHelper = RegistryCenter.get(HibernateHelper.class);
        hibernateHelper.initialize(this);
        ProfessionalCenter professionalCenter = RegistryCenter.get(ProfessionalCenter.class);
        professionalCenter.initialize(this);
    }

    private void initLogger(String name) {
        try {
            Logger logger;
            if (name == null || name.isEmpty()) {
                logger = org.apache.logging.log4j.LogManager.getLogger();
            } else {
                logger = org.apache.logging.log4j.LogManager.getLogger(name);
            }
            logger.info("Entering Application");
            logger.error("SOMETHING!!!");
            logger.info("Starting " + logger.getName());
        } catch (Exception e) {
            System.out.println(Utilities.exceptionToString(e));
        }
    }


    private void getAppender(String filename) {
//        RollingFileAppender appender;
//            appender = RollingFileAppender.createAppender(filename, "%-5p %c %t%n%29d - %m%n", "True", "RootAppender",
//                    "True", "True", new OnStartupTriggeringPolicy(), DefaultRolloverStrategy.createStrategy("5", "1", filename, "0", new DefaultConfiguration()), null, null, "True",
//                    "False", "", new DefaultConfiguration());
//        return appender;

    }

}
