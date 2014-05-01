package com.smallwood.projectx;

import com.smallwood.projectx.helpers.HibernateHelper;
import com.smallwood.projectx.helpers.Utilities;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

import java.util.*;

/**
 * Created by bigwood928 on 4/14/14.
 */
public class ProfessionalCenter {

    private Map<Long, Professional> professionals;
    private Set<Professional> professionalsSet;
    private Initializer initializer;


    public ProfessionalCenter() {
        professionals = new HashMap<Long, Professional>();
        professionalsSet = new HashSet<Professional>();
    }

    public void initialize(Initializer initializer) {
        this.initializer = initializer;
        if(Boolean.parseBoolean(initializer.getInitParameter("createDatabase"))) {
            createFakeProfessionals();
        } else {
            HibernateHelper helper = RegistryCenter.get(HibernateHelper.class);
            Session session = helper.getSession();
            SQLQuery query = session.createSQLQuery("Select id from professionals");
            for(Object id : query.list()) {
                Professional professional = (Professional) session.get(Professional.class, Long.parseLong(id.toString()));
                professionals.put(professional.getId(), professional);
            }
            session.close();
        }

    }

    private void createFakeProfessionals() {
        HashSet<String> skills = new HashSet<String>();
        skills.add("OSX Lion");
        skills.add("Unix");
        skills.add("C++");
        skills.add("Java");
        Professional professional = new Professional("Steve", "Jobs", "rdsmallwood928@gmail.com", "Reed", skills);

        HashSet<String> moreSkills = new HashSet<String>();
        moreSkills.add("Windows 8");
        moreSkills.add("C#");
        moreSkills.add("Visual Basic");
        moreSkills.add("Excel");
        moreSkills.add("C++");
        Professional professional1 = new Professional("Bill", "Gates", "rdsmallwood928@gmail.com", "Harvard", moreSkills);

        addProfessional(professional);
        addProfessional(professional1);

    }


    public Collection<Professional> getProfessionals() {
        return professionals.values();
    }

    public void addProfessional(Professional professional) {
        try {
            HibernateHelper hibernateHelper = RegistryCenter.get(HibernateHelper.class);
            Session session = hibernateHelper.getSession();
            session.beginTransaction();
            session.persist(professional);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception ex){
            System.out.println(Utilities.exceptionToString(ex));
        }
        professionals.put(professional.getId(), professional);
        professionalsSet.add(professional);
    }

    public Professional getProfessionalById(Long id) {
        return professionals.get(id);
    }

    public Professional verifyProfessional(Professional professional) {
        Professional knownProfessional = professionals.get(professional);
        if(knownProfessional.getPassword().equals(professional.getPassword())) {
            return knownProfessional;
        }
        return Professional.EMPTY;
    }
}
