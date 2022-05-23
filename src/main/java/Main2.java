import Key.KeyLinkedPurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();

//        Course course = session.get(Course.class,3);
//        System.out.println("Course ID: " + course.getId() + " - " + course.getName() + ". students count: " + course.getStudentsCount());
//
//        Teacher teachers = session.get(Teacher.class,5);
//        System.out.println(teachers.getName() + " Salary: " + teachers.getSalary() + " age = " + teachers.getAge());

//        Course course1 = session.get(Course.class,1);
//        List<Student> studentsList = course1.getStudents();
//        for (Student student: studentsList){
//            System.out.println(student.getName());
//        }

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Course> query = builder.createQuery(Course.class);
//        Root<Course> root = query.from(Course.class);
//        query.select(root).where(builder.greaterThan(root.<Integer>get("price"),100000))
//                .orderBy(builder.desc(root.get("price")));
//        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//        for (Course course: courseList){
//            System.out.println(course.getName() + " - " + course.getPrice());
//        }

        String hqlPurchase = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaselists = session.createQuery(hqlPurchase).getResultList();

        session.beginTransaction();
        for (PurchaseList purchaselist : purchaselists){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Course> courseQuery = builder.createQuery(Course.class);
            Root<Course> courseRoot = courseQuery.from(Course.class);
            courseQuery.select(courseRoot).where(builder.equal(courseRoot.get("name"),purchaselist.getCourseName()));
            Course course = session.createQuery(courseQuery).getSingleResult();

            CriteriaQuery<Student> studentQuery = builder.createQuery(Student.class);
            Root<Student> studentRoot = studentQuery.from(Student.class);
            studentQuery.select(studentRoot).where(builder.equal(studentRoot.get("name"),purchaselist.getStudentName()));
            Student student = session.createQuery(studentQuery).getSingleResult();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(new KeyLinkedPurchaseList(course.getId(), student.getId()));
            linkedPurchaseList.setCourseId(course.getId());
            linkedPurchaseList.setStudentId(student.getId());
            session.save(linkedPurchaseList);

        }


        //transaction.commit();
        sessionFactory.close();
        session.close();
    }
}
