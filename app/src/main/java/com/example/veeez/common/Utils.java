package com.example.veeez.common;

import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

public class Utils {

    public static String formatPrice(double number) {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number)+" ریال";
        return formattedNumber;
    }

}
