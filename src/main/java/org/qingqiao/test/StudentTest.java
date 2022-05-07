package org.qingqiao.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.qingqiao.bean.Clazz;
import org.qingqiao.bean.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudentTest {
    private static Connection conn = null;

    static {
        FileInputStream fs = null;
        Properties ps;
        try {
            fs = new FileInputStream("D:\\HelloWorld\\JDBCDemo1\\src\\main\\resources\\db.properties");
            ps = new Properties();
            ps.load(fs);
            DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(ps);
            conn = dataSource.getConnection();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public List<Student> showALl() throws Exception {
        ArrayList<Student> sl = new ArrayList<Student>();
        PreparedStatement ps = conn.prepareStatement("select s.*,c.name from student1 s,clazz c where s.cid=c.id");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Student studentDemo = new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6),new Clazz(rs.getInt(7), rs.getString(8)));
            sl.add(studentDemo);
        }

        return sl;
    }

    public int insertStudent(Student student) throws SQLException {
        int i = 0;
        PreparedStatement ps = conn.prepareStatement("insert into student1 values(null,?,?,?,?,?,?)");
        ps.setString(1, student.getUsername());
        ps.setString(2,student.getPassword());
        ps.setString(3, student.getSex());
        ps.setInt(4, student.getAge());
        ps.setString(5, student.getAddress());
        ps.setInt(6, student.getClazz().getId());
        i = ps.executeUpdate();
        return i;
    }

    public int updateStudent(Student student, int id) throws Exception {
        int i = 0;
        PreparedStatement ps = conn.prepareStatement("update student1 set username=?,password=?,sex=?,age=?,address=?,cid=? where id=?");
        ps.setString(1, student.getUsername());
        ps.setString(2,student.getPassword());
        ps.setString(3,student.getSex());
        ps.setInt(4,student.getAge());
        ps.setString(5,student.getAddress());
        ps.setInt(6,student.getClazz().getId());
        ps.setInt(7,id);
        i = ps.executeUpdate();
        return i;
    }
    public int deleteStudent(int id) throws Exception {
        int i=0;
        PreparedStatement ps= conn.prepareStatement("delete from student1 where id=?");
        ps.setInt(1,id);
        i = ps.executeUpdate();
        return i;
    }
}
