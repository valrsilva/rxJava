
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Created by valdeci on 13/09/2016.
 */
public class ConnectionSubscription implements Subscription {

    private final Subscription sub;

    private final Connection connection;
    private final HashSet<Statement> statements = new HashSet<>();
    private final HashSet<ResultSet> resultSets = new HashSet<>();

    public void unsubscribe(){

        System.out.println("unsubscribe called...");

        for(ResultSet rs: resultSets){

            System.out.println("closing resultSet: " + rs.toString());
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(Statement s: statements){

            System.out.println("closing statement: " + s.toString());
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null){

            System.out.println("closing statement: " + connection.toString());
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sub.unsubscribe();

    }

    public ConnectionSubscription(Connection connection){
        this.connection = connection;

        this.sub = Subscriptions.create(() -> {
            System.out.println("create.....");
        });

    }

    public void registerResourcesForClose(Statement s){
        statements.add(s);
    }

    public void registerResourcesForClose(ResultSet rs){
        resultSets.add(rs);
    }

    public boolean isUnsubscribed() {
        return sub.isUnsubscribed();
    }

    public Connection getConnection(){
        return connection;
    }

}
