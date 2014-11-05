import com.thoughtworks.iamcoach.pos.service.BoughtItemService;
import com.thoughtworks.iamcoach.pos.util.FileUtil;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    private static final String CART_FILE = "src/main/resources/cart.txt";
    private static final String SPRING_CONFIG_FILE = "applicationContext.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        BoughtItemService boughtItemServiceSpring = (BoughtItemService) applicationContext.getBean("boughtItemService");
        List<String> cartBarcodes = FileUtil.textToList(CART_FILE);

        List<BoughtItem> boughtItems = boughtItemServiceSpring.barcodesToBoughtItems(cartBarcodes);
        Output.printShoppingList(boughtItems);
    }

}
