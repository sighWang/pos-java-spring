package com.thoughtworks.iamcoach.pos;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class BarcodeTest {
    private Barcode barcode = new Barcode();

    @Test
    public void unique_barcodes_test() {
        List<String> barcodeList = new ArrayList<String>();
        barcodeList.add("ITEM000001");
        barcodeList.add("ITEM000001");
        barcodeList.add("ITEM000002");

        List<String> uniqueBarcodes = barcode.uniqueBarcode(barcodeList);
        assertThat(uniqueBarcodes.get(0)).isEqualTo("ITEM000001");
        assertThat(uniqueBarcodes.get(1)).isEqualTo("ITEM000002");
        assertThat(uniqueBarcodes.size()).isEqualTo(2);
    }

    @Test
    public void get_barcode_times_test() {
        List<String> barcodeList = new ArrayList<String>();
        barcodeList.add("ITEM000001");
        barcodeList.add("ITEM000002");
        barcodeList.add("ITEM000002");

        assertThat(barcode.getBarcodeTimes(barcodeList, "ITEM000002")).isEqualTo(2);
        assertThat(barcode.getBarcodeTimes(barcodeList, "ITEM000001")).isEqualTo(1);
    }

    @Test
    public void get_barcode_number_test() {
        assertThat(barcode.getBarcodeNumber("ITEM000002", 1)).isEqualTo(1.0);
        assertThat(barcode.getBarcodeNumber("ITEM000002-3", 1)).isEqualTo(3.0);
    }

    @Test
    public void get_item_barcode_test() {
        assertThat(barcode.getItemBarcode("ITEM000002")).isEqualTo("ITEM000002");
        assertThat(barcode.getItemBarcode("ITEM000002-1")).isEqualTo("ITEM000002");
    }

}
