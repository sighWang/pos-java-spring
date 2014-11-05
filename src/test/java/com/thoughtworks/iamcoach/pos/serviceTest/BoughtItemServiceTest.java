package com.thoughtworks.iamcoach.pos.serviceTest;

import com.thoughtworks.iamcoach.pos.service.BoughtItemService;
import com.thoughtworks.iamcoach.pos.domain.BoughtItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class BoughtItemServiceTest {
    private BoughtItemService boughtItemService = new BoughtItemService();

    @Test
    public void barcodes_to_boughtItems_test() {
        List<String> cartBarcodes = new ArrayList<String>();
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000001");
        cartBarcodes.add("ITEM000006-2.00");
        cartBarcodes.add("ITEM000006-2.00");


        List<BoughtItem> boughtItemList = boughtItemService.barcodesToBoughtItems(cartBarcodes);
        BoughtItem boughtItem1 = boughtItemList.get(0);
        BoughtItem boughtItem2 = boughtItemList.get(1);

        assertThat(boughtItem1.getNumber()).isEqualTo(4.00);
        assertThat(boughtItem1.getBarcode()).isEqualTo("ITEM000001");

        assertThat(boughtItem2.getNumber()).isEqualTo(4.00);
        assertThat(boughtItem2.getBarcode()).isEqualTo("ITEM000006");

    }

}
