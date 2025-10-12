package jachu.pg.auilabs.lab1;

public class main {
    public static void main(String[] args) {

        Category category_one = Category.builder()
                .name("cat1")
                .someValue(4)
                .build();

        Category category_two = Category.builder()
                .name("cat2")
                .someValue(62)
                .build();

        Element element_one = Element.builder()
                .name("el1")
                .value(5)
                .category(category_one)
                .build();

        Element element_two = Element.builder()
                .name("el2")
                .value(15)
                .build();

    }
}
