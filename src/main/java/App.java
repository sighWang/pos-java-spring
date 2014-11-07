import com.thoughtworks.iamcoach.pos.service.BoughtItemService;
import com.thoughtworks.iamcoach.pos.util.FileUtil;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Set;

public class App {
    private static final String CART_FILE = "src/main/resources/cart.txt";
    private static final String SPRING_CONFIG_FILE = "applicationContext.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);
        BoughtItemService boughtItemService = (BoughtItemService) applicationContext.getBean("boughtItemService");
        List<String> cartBarcodes = FileUtil.textToList(CART_FILE);

        List<BoughtItem> boughtItems = boughtItemService.barcodesToBoughtItems(cartBarcodes);
        Set<String> categorySet = boughtItemService.getCategorySetOfBoughtItems(boughtItems);
        Output.printShoppingList(boughtItems, categorySet);
    }

}
