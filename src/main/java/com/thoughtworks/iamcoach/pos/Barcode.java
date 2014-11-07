package com.thoughtworks.iamcoach.pos;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Barcode {

    public String getItemBarcode(String cartBarcode) {
        String[] barcodes = cartBarcode.split("-");
        return barcodes[0];
    }

    public double getBarcodeNumber(String cartBarcode, int times) {
        String[] barcodes = cartBarcode.split("-");
        Double number = 1.00;

        if (barcodes.length == 2) {
            number = Double.parseDouble(barcodes[1]);
        }

        return number * times;
    }

    public Set<String> uniqueBarcode(List<String> cartBarcodes) {
        Set<String> barcodeList = new HashSet<String>();
        barcodeList.addAll(cartBarcodes);

        return barcodeList;
    }

    public int getBarcodeTimes(List<String> cartBarcodes, String barcode) {
        int result = 0;

        for (String cartBarcode : cartBarcodes) {
            if (cartBarcode.equals(barcode)) {
                result++;
            }
        }

        return result;
    }
}
