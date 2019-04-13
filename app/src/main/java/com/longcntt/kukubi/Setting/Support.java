package com.longcntt.kukubi.Setting;

import java.util.ArrayList;
import java.util.Random;

public class Support {

    private Random r = new Random();
    public static int result;
    private String[] mau = new String[]{
            "#D82020", "#99FF00", "#6500FF",
            "#0099FF", "#003322", "#FF0099",
            "#8080FF", "#FFFE59", "#FF0B0E"};

    public ArrayList<String> TaoMau(int n) {
        int x, y;
        ArrayList<String> a = new ArrayList<>();
        x = r.nextInt(n);
        y = r.nextInt(3);
        result = x;
        for (int i = 0; i < n; i++) {
            if (i == x) {
                a.add(mau[y]);
            } else {
                a.add(mau[y + 1]);
            }
        }
        return a;
    }

}
