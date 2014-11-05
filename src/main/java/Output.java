import com.thoughtworks.iamcoach.pos.domain.PrintItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Output {
    public static void printShoppingList(List<PrintItem> printItemList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 - HH:mm");
        String date = formatter.format(LocalDateTime.now());

        String shoppingList = "**********沃尔玛超市**********\n";
        shoppingList += date + "\n\n";
        shoppingList += getShoppingListBody(printItemList);
        shoppingList += "优惠前金额: " + getSum(printItemList) + "元 \n";
        shoppingList += "优惠后金额: " + getTotal(printItemList) + "元 \n";
        shoppingList += "优惠差价: " +
                (getSum(printItemList) - getTotal(printItemList)) + "元";

        System.out.println(shoppingList);
    }

    private static Double getTotal(List<PrintItem> printItemList) {
        Double total = 0.00;

        for (PrintItem printItem : printItemList) {
            total += printItem.getSubtotal();
        }

        return total;
    }

    private static Double getSum(List<PrintItem> printItemList) {
        Double sum = 0.00;

        for (PrintItem printItem : printItemList) {
            sum += printItem.getPrice() * printItem.getNumber();
        }

        return sum;
    }

    private static String getShoppingListBody(List<PrintItem> printItemList) {
        String shoppingListBody = "";

        for (PrintItem printItem : printItemList) {
            shoppingListBody += printItem.getName() + " ";
            shoppingListBody += printItem.getNumber();
            shoppingListBody += printItem.getUnit() + " ";
            shoppingListBody += printItem.getPrice() + "元 ";
            shoppingListBody += printItem.getSubtotal() + "元\n";
        }

        return shoppingListBody;
    }
}
