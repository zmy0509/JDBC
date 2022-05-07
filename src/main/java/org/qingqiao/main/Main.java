package org.qingqiao.main;

import org.qingqiao.bean.Clazz;
import org.qingqiao.bean.Student;
import org.qingqiao.test.StudentTest;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentTest st = new StudentTest();
        List<Student> students = st.showALl();
        //Student student = new Student(0, "bazhang", "888", "男", 22, "漠河", new Clazz(2,"二班"));
        // int i = st.insertStudent(student);
        //Student student = new Student(0, "张", "", "", 0, "", new Clazz(2, null));
        //int i1 = st.updateStudent(student,1);
        /*if (i>0){
            for (Student student1 : students) {
                System.out.println(student1);
            }*/
        st.deleteStudent(1);
        for (Student student1 : students) {
            System.out.println(student1);
        }
    }
}
