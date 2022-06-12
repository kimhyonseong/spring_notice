package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;

@Repository
public class DBconnect {
//    @Autowired
//    DataSource ds;

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new GenericXmlApplicationContext("file:web/WEB-INF/config/applicationContext.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection();

        System.out.println("conn = " + conn);
    }
}
