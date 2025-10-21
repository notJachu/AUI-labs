package jachu.pg.auilabs.components;

import jachu.pg.auilabs.services.CategoryService;
import jachu.pg.auilabs.services.WheelBarrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CliApp implements CommandLineRunner {

    private final CategoryService categoryService;
    private final WheelBarrowService wheelBarrowService;

    @Autowired
    public CliApp(CategoryService categoryService, WheelBarrowService wheelBarrowService) {
        this.categoryService = categoryService;
        this.wheelBarrowService = wheelBarrowService;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            String command = sc.nextLine();
            if (command.equals("pa")) {
                System.out.println("Categories:");
                categoryService.findAll().forEach(System.out::println);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
