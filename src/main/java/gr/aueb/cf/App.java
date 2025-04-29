package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;

public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
//        Teacher teacher = new Teacher(null, "Bob", "W.", null);
//        Course cplusplus = new Course(null, "C++", null);
//        teacher.addCourse(cplusplus);

        em.getTransaction().begin();

//        Course course = em.find(Course.class, 2L);
//        em.remove(course);

//        Teacher alice = em.find(Teacher.class, 1L);
//        Course databases = new Course(null, "Databases", null);
        // alice.setLastname("Wonderland");
        //alice.addCourse(databases);

        //em.persist(databases);
        //em.merge(alice);

//        em.persist(teacher);
//        em.persist(cplusplus);

        // select all teachers
//        String sql = "SELECT t FROM Teacher t";
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);
//
//        // select all courses
//        String sql2 = "SELECT c FROM Course c";
//        TypedQuery<Course> query2 = em.createQuery(sql2, Course.class);
//        List<Course> courses = query2.getResultList();
//        courses.forEach(System.out::println);
//
//        // select courses that a certain teacher teaches
//        String sql3 = "SELECT c FROM Course c WHERE c.teacher.firstname = :firstname";
//        List<Course> coursesByTeacher = em.createQuery(sql3, Course.class)
//                .setParameter("firstname", "Μάρκος")
//                .getResultList();
//        coursesByTeacher.forEach(System.out::println);
//
//        // select teachers and titles of courses they teach
//        String sql4 = "SELECT t, c.title FROM Teacher t JOIN t.courses c"; // INNER JOIN
//        TypedQuery<Object[]> query4 = em.createQuery(sql4, Object[].class);
//        List<Object[]> results = query4.getResultList();
//        for (Object[] result : results) {
//            Teacher teacher = (Teacher) result[0];
//            String courseTitle = (String) result[1];
//            System.out.println("Teacher: " + teacher.getLastname() + ", Course: " + courseTitle);
//        }

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class); // what it returns
//        Root<Teacher> teacher = query.from(Teacher.class); // from
//
//        query.select(teacher); // must be the same as CriteriaQuery
//        List<Teacher> teachers = em.createQuery(query).getResultList();
//        teachers.forEach(System.out::println);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<String> query = cb.createQuery(String.class);
//        Root<Course> course = query.from(Course.class);
//        query.select(course.get("title"));
//        List<String> titles = em.createQuery(query).getResultList();
//        titles.forEach(System.out::println);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> teacher = query.from(Teacher.class);
//        ParameterExpression<String> lastname = cb.parameter(String.class);
//        query.select(teacher).where(cb.equal(teacher.get("lastname"), lastname));
//        List<Teacher> teachers = em.createQuery(query).setParameter(lastname, "Μόσχος").getResultList();
//        teachers.forEach(System.out::println);

        //courses (title) and teachers (firstname, lastname) that teach them
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Course> course = query.from(Course.class);
        Join<Course, Teacher> teacher = course.join("teacher");
        query.multiselect(course.get("title"), teacher.get("lastname"), teacher.get("firstname"));
        List<Object[]> coursesTeachers = em.createQuery(query).getResultList();

        for (Object[] result : coursesTeachers) {
            String title = (String) result[0];
            String lastname = (String) result[1];
            String firstname = (String) result[2];
            System.out.println("Title: " + title + ", Lastname: " + lastname + ", Firstname: " + firstname);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}