import com.thoughtworks.iamcoach.pos.domain.BoughtItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Output {
    public static void printShoppingList(List<BoughtItem> BoughtItemList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 - HH:mm");
        String date = formatter.format(LocalDateTime.now());

        String shoppingList = "**********沃尔玛超市**********\n";
        shoppingList += date + "\n\n";
        shoppingList += getShoppingListBody(BoughtItemList);
        shoppingList += "优惠前金额: " + getSum(BoughtItemList) + "元 \n";
        shoppingList += "优惠后金额: " + getTotal(BoughtItemList) + "元 \n";
        shoppingList += "优惠差价: " +
                (getSum(BoughtItemList) - getTotal(BoughtItemList)) + "元";

        System.out.println(shoppingList);
    }

    private static Double getTotal(List<BoughtItem> BoughtItemList) {
        Double total = 0.00;

        for (BoughtItem BoughtItem : BoughtItemList) {
            total += BoughtItem.getSubtotal();
        }

        return total;
    }

    private static Double getSum(List<BoughtItem> BoughtItemList) {
        Double sum = 0.00;

        for (BoughtItem BoughtItem : BoughtItemList) {
            sum += BoughtItem.getPrice() * BoughtItem.getNumber();
        }

        return sum;
    }

    private static String getShoppingListBody(List<BoughtItem> boughtItemList) {
        String shoppingListBody = "";

        for (BoughtItem boughtItem : boughtItemList) {
            shoppingListBody += boughtItem.toString() + "\n";
        }

        return shoppingListBody;
    }
}
