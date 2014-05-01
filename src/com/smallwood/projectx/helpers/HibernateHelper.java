package com.smallwood.projectx.helpers;

import com.smallwood.projectx.Initializer;
import com.smallwood.projectx.Professional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * Created by bigwood928 on 4/18/14.
 */
public class HibernateHelper {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    public void initialize(Initializer initializer) {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.setProperty("hibernate.c3p0.min_size", "1");
        props.setProperty("hibernate.c3p0.max_size", "5");
        props.setProperty("hibernate.c3p0.timeout", "300");
        props.setProperty("hibernate.c3p0.max_statements", "50");
        props.setProperty("hibernate.c3p0.idle_test_period", "300");
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/projectx");
        props.setProperty("hibernate.connection.username", "bigwood928");
        props.setProperty("hibernate.connection.password", "uk2003");
        props.setProperty("hibernate.show_sql", "true");
        if(Boolean.parseBoolean(initializer.getInitParameter("createDatabase"))) {
            props.setProperty("hibernate.hbm2ddl.auto", "create");
        }
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Professional.class);
        configuration.setProperties(props);
        configuration.configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public Session getSession() {
        return sessionFactory.withOptions().openSession();
    }

}
