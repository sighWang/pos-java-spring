package com.thoughtworks.iamcoach.pos;

import java.util.List;

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
