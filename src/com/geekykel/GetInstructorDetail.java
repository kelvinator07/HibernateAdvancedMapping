package com.geekykel;

import com.geekykel.entities.Instructor;
import com.geekykel.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetail {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{

            // start a transaction
            session.beginTransaction();

            // get instructor by primary key / id
            int id = 3;

            // get the instructor detail object
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            // print the instructor detail
            System.out.println("Instructor Detail: " + instructorDetail);

            // print  the associated instructor
            System.out.println("Associated Instructor: " + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // handle/close connection leak issue
            session.close();

            factory.close();
        }

    }
}
