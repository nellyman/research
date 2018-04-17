package com.nbh.scratch;

import java.util.ArrayList;
import java.util.List;

public class Stuff {

    public static void main(String[] args) {
        int l=2,r=7;
        List<Integer> odds = new ArrayList<>();
        for (int i=l; i<=r;i++){
            if ((i % 2)>0){
                odds.add(i);
            }
        }
        int[] stuff = odds.stream().mapToInt(i->i).toArray();
    }
}
