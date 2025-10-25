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

    @Autowired
    public CliApp(CategoryService categoryService, WheelBarrowService wheelBarrowService, SessionFactory sessionFactory) {
        this.categoryService = categoryService;
        this.wheelBarrowService = wheelBarrowService;
    }

    protected void addWheelbarrow(String name) {
        // Hibernate.initialize(category);
        Category category = categoryService.findAll().get(0);
        Hibernate.initialize(category.getWheelBarrows());
        WheelBarrow wheelBarrow = WheelBarrow.builder().name(name).price(100).category(category).build();
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

    private void printAllCategories() {
        List<Category> categories = categoryService.findAll();
        System.out.print("All categories: ");
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isRunning = true;
        try (Scanner sc = new Scanner(System.in)) {
            while (isRunning) {
                String command = sc.nextLine();

                switch (command.toLowerCase()) {
                    case "exit":
                        isRunning = false;
                        break;
                    case "pab":
                        printAllBarrows();
                        break;
                    case "pac":
                        printAllCategories();
                        break;
                    case "pbc":
                        System.out.println("Wheelbarrows by category:");
                        List<Category> categories = categoryService.findAll();
                        for (Category category : categories) {
                            List<WheelBarrow> wheelBarrows = wheelBarrowService.findAllByCategory(category);
                            System.out.println("Category: " + category.getName());
                            for (WheelBarrow wheelBarrow : wheelBarrows) {
                                System.out.println(" - " + wheelBarrow);
                            }
                        }
                        break;
                    case "awb":
                        System.out.println("Input name");
                        String name = sc.nextLine();
                        addWheelbarrow(name);
                        break;
                    default:
                        System.out.println("Unknown command");
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
