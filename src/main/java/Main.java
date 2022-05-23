import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Europe/Moscow";
        String user = "root";
        String pass = "kolokolnin";
        try{
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement();
            String sqlQuery = "select course_name, \n" +
                    "count(course_name) / ( max(month(subscription_date)) - min(month(subscription_date))  )as AVG_sales\n" +
                    "from purchaselist\n" +
                    "group by course_name";
           ResultSet resultSet =  statement.executeQuery(sqlQuery);
           while (resultSet.next()){
              String avg = resultSet.getString("course_name") +" - "+ resultSet.getString("AVG_sales");
               System.out.println(avg);
           }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
