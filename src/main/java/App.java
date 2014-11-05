import com.thoughtworks.iamcoach.pos.service.BoughtItemService;
import com.thoughtworks.iamcoach.pos.util.FileUtil;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import com.thoughtworks.iamcoach.pos.domain.PrintItem;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String CART_FILE = "src/main/resources/cart.txt";

    public static void main(String[] args) {
        BoughtItemService boughtItemService = new BoughtItemService();
        List<String> cartBarcodes = FileUtil.textToList(CART_FILE);

        List<BoughtItem> boughtItems = boughtItemService.barcodesToBoughtItems(cartBarcodes);

        List<PrintItem> printItemList = new ArrayList<PrintItem>();

        for (BoughtItem boughtItem : boughtItems) {
            PrintItem printItem = new PrintItem(boughtItem);
            printItemList.add(printItem);
        }

        Output.printShoppingList(printItemList);
    }

}
