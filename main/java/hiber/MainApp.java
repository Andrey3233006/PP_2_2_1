package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User bob = new User("Bob","Dillan","dillan@gmail.com");
      User adriano = new User("Adriano","Chelentano","chelentano@gmail.com");
      User richy = new User("Richy","Blackmor","blackmor@gmail.com");
      User luchiano = new User("Luchiano","Povarotty","luchano@gmail.com");

      Car vaz = new Car("VAZ", 2101);
      Car gaz = new Car("GAZ", 21);
      Car zaz = new Car("ZAZ", 966);
      Car uaz = new Car("UAZ", 469);

      userService.add(bob.setCar(vaz).setUser(bob));
      userService.add(adriano.setCar(gaz).setUser(adriano));
      userService.add(richy.setCar(zaz).setUser(richy));
      userService.add(luchiano.setCar(uaz).setUser(luchiano));

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println();
         System.out.println("First Name: " + user.getFirstName());
         System.out.println("Last Name: " + user.getLastName());
         System.out.println("Email: " + user.getEmail());
         System.out.println(user.getCar());
      }

      System.out.println();
      System.out.println(userService.getUserByCar("VAZ", 2101));
      System.out.println();
      System.out.println(userService.getUserByCar("ZAZ", 966));

      context.close();
   }
}
