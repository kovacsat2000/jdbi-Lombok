package user;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();

            User user1 = User.builder()
                    .username("johnny21")
                    .password("sheepsin")
                    .name("Johnny James")
                    .email("johnny.james2000@gmail.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("2000-11-08"))
                    .enabled(true)
                    .build();

            dao.insertUser(user1);

            User user2 = User.builder()
                    .username("janyjane")
                    .password("johnmyhb")
                    .name("Jane Smith")
                    .email("jane.smith1972@gmail.com")
                    .gender(User.Gender.FEMALE)
                    .dob(LocalDate.parse("1972-04-27"))
                    .enabled(false)
                    .build();

            dao.insertUser(user2);

            User user3 = User.builder()
                    .username("jomagam")
                    .password("akarmibarmi")
                    .name("Csak En")
                    .email("csak.en2000@gmail.com")
                    .gender(User.Gender.MALE)
                    .dob(LocalDate.parse("2000-10-11"))
                    .enabled(true)
                    .build();

            dao.insertUser(user3);

            System.out.println(dao.findById("1"));

            System.out.println(dao.findByUsername("janyjane"));

            System.out.println("-----------------------");

            dao.listUsers().stream().forEach(System.out::println);

            System.out.println("-----------------------");

            dao.deleteUser("jomagam");

            dao.listUsers().stream().forEach(System.out::println);
        }
    }
}
