import com.thoughtworks.iamcoach.pos.domain.BoughtItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class Output {
    public static void printShoppingList(List<BoughtItem> boughtItemList, Set<String> categorySet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 - HH:mm");
        String date = formatter.format(LocalDateTime.now());

        String shoppingList = "**********沃尔玛超市**********\n";
        shoppingList += date + "\n\n";
        shoppingList += getShoppingListBody(boughtItemList, categorySet);
        shoppingList += "优惠前金额: " + getSum(boughtItemList) + "元 \n";
        shoppingList += "优惠后金额: " + getTotal(boughtItemList) + "元 \n";
        shoppingList += "优惠差价: " +
                (getSum(boughtItemList) - getTotal(boughtItemList)) + "元";

        System.out.println(shoppingList);
    }

    private static Double getTotal(List<BoughtItem> boughtItemList) {
        Double total = 0.00;

        for (BoughtItem boughtItem : boughtItemList) {
            total += boughtItem.getSubtotal();
        }

        return total;
    }

    private static Double getSum(List<BoughtItem> boughtItemList) {
        Double sum = 0.00;

        for (BoughtItem boughtItem : boughtItemList) {
            sum += boughtItem.getPrice() * boughtItem.getNumber();
        }

        return sum;
    }

    private static String getShoppingListBody(List<BoughtItem> boughtItemList, Set<String> categorySet) {

        String shoppingListBody = "";

        for (String category : categorySet) {
            shoppingListBody += category + "\n";
            for (BoughtItem boughtItem : boughtItemList) {
                if (category.equals(boughtItem.getCategory())) {
                    shoppingListBody += boughtItem.toString();
                }
            }
            shoppingListBody += "\n";
        }
        return shoppingListBody;
    }
}
