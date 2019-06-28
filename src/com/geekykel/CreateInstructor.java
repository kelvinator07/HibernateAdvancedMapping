package com.geekykel;

import com.geekykel.entities.Course;
import com.geekykel.entities.Instructor;
import com.geekykel.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            Instructor instructor = new Instructor("Mary","Morah", "mmary@geekykel.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "http://www.youtube.com/marymorah",
                            "Swimming");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);


            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();

            factory.close();
        }


    }
}
