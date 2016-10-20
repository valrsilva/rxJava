import org.apache.commons.io.FileUtils;
import rx.Observable;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by valdeci on 13/09/2016.
 */
public class TestDataBase {

    public static void init() {

        File databaseDirectory = new File("C:/temp/pluralSight");

        try {
            FileUtils.deleteDirectory(databaseDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection c = null;

        try {
            DriverManager.getConnection("jdbc:derby:pluralSightTest;create=true");
            c = DriverManager.getConnection("jdbc:derby:pluralSightTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement s = null;

        try {

            s = c.createStatement();

            try{
                String sql = "CREATE TABLE GREEK_ALPHABET (ID BIGINT, LETTER VARCHAR(10))";
                s.execute(sql);
            }catch(Exception e){

            }

            String sqlDel = "DELETE FROM GREEK_ALPHABET";
            s.execute(sqlDel);

            int id=1;
            for(String letter: DataGenerator.generateAlphabet()){
                String sql = "INSERT INTO GREEK_ALPHABET(ID, LETTER) values (" + id + ",'" + letter + "' )";
                s.execute(sql);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

            if (s != null){
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static Observable<String> selectGreekAlphabet(ConnectionSubscription connectionSubscription){

        try{

            Statement s = connectionSubscription.getConnection().createStatement();
            connectionSubscription.registerResourcesForClose(s);

            ResultSet rs = s.executeQuery("SELECT LETTER FROM GREEK_ALPHABET");
            connectionSubscription.registerResourcesForClose(rs);

            ArrayList<String> returnList = new ArrayList<>();
            while(rs.next()){
                returnList.add(rs.getString("LETTER"));
            }

            return Observable.from(returnList);

        }catch(Exception e){

           throw new RuntimeException("erro no select...");

        }

    }

    public static Connection createConnection(){

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection c = null;

        try {
            DriverManager.getConnection("jdbc:derby:pluralSightTest;create=true");
            c = DriverManager.getConnection("jdbc:derby:pluralSightTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

}
