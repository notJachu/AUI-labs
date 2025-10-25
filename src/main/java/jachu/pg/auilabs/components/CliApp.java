package jachu.pg.auilabs.components;

import jachu.pg.auilabs.lab1.Category;
import jachu.pg.auilabs.lab1.WheelBarrow;
import jachu.pg.auilabs.services.CategoryService;
import jachu.pg.auilabs.services.WheelBarrowService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CliApp implements CommandLineRunner {

    private final CategoryService categoryService;
    private final WheelBarrowService wheelBarrowService;
    private final SessionFactory sessionFactory;

    @Autowired
    public CliApp(CategoryService categoryService, WheelBarrowService wheelBarrowService, SessionFactory sessionFactory) {
        this.categoryService = categoryService;
        this.wheelBarrowService = wheelBarrowService;
        this.sessionFactory = sessionFactory;
    }

    protected void addWheelbarrow() {
        // Hibernate.initialize(category);
        Category category = categoryService.findAll().get(0);
        Hibernate.initialize(category.getWheelBarrows());
        WheelBarrow wheelBarrow = WheelBarrow.builder()
                .name("new barrow").price(100)
                .category(category)
                .build();
        // categoryService.save(category);
        wheelBarrowService.save(wheelBarrow);
    }

    protected void printAllBarrows() {
        List<WheelBarrow> wheelBarrows = wheelBarrowService.findAll();
        System.out.print("All barrows: ");
        for (WheelBarrow wheelBarrow : wheelBarrows) {
            System.out.println(wheelBarrow);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            String command = sc.nextLine();
            if (command.equals("pa")) {
                System.out.println("Categories:");
                categoryService.findAll().forEach(System.out::println);
                List<Category> categories = categoryService.findAll();
                printAllBarrows();
                System.out.println("Wheelbarrows by category:");
                for (Category category : categories) {
                    List<WheelBarrow> wheelBarrows = wheelBarrowService.findAllByCategory(category);
                    System.out.println("Category: " + category.getName());
                    for (WheelBarrow wheelBarrow : wheelBarrows) {
                        System.out.println(" - " + wheelBarrow);
                    }
                }
                addWheelbarrow();
                printAllBarrows();

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
