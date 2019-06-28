package com.geekykel;

import com.geekykel.entities.Course;
import com.geekykel.entities.Instructor;
import com.geekykel.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourse {

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

            session.beginTransaction();

            int id = 2;

            Instructor instructor = session.get(Instructor.class, id);

            Course course1 = new Course("COM 101");
            Course course2 = new Course("COM 202");
            Course course3 = new Course("COM 303");

            instructor.add(course1);
            instructor.add(course2);
            instructor.add(course3);

            session.save(course1);
            session.save(course2);
            session.save(course3);

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
