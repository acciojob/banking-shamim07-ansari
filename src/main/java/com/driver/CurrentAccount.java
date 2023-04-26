package com.driver;

import java.util.*;

class Pair {
    Character key;
    Integer value;

    public Pair(Character key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, double minBalance) {

        super(name, balance, minBalance);
    }

    public CurrentAccount(String name, double balance) {

        super(name, balance, 5000);
    }


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance);

        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean ans = noAdjacentCharacters();

        if (ans == false) {

            boolean isValid = createValidId();

            if (isValid == false) {

                throw new Exception("Valid License can not be generated");
            }
        }

    }

    public boolean createValidId() {

        int n = tradeLicenseId.length();

        // A A A A B B B B B B B B B B B B B C C C D H G G G H H

        HashMap<Character, Integer> hashMap = new HashMap<>();

        String ans = "";

        for (int i = 0; i < n; i++) {

            Character key = tradeLicenseId.charAt(i);

            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }


//        char maxRepeatedChar = ' ';
        int maxRepeatFreq = 0;

        Pair p = new Pair(' ', 0);

        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {

            Character key = entry.getKey();
            int value = entry.getValue();

            if (value > maxRepeatFreq) {
//                maxRepeatedChar = key;
                maxRepeatFreq = value;

                p.key = key;
                p.value = value;
            }

        }

        if (n % 2 == 0 && maxRepeatFreq >= (n / 2) + 1) {
            return false;
        }else if (n % 2 == 1 && maxRepeatFreq > (n + 1) / 2) {
            return false;
        }

        rearrangeCharacters(hashMap, p, n);

        return true;
    }

    private void rearrangeCharacters(HashMap<Character, Integer> hashMap, Pair pair, int n) {

        char[] ans = new char[n];
        int index = 0;


        while (hashMap.get(pair.key) > 0) {

            // dec value and put char in ans string
            ans[index] = pair.key;
            index = index + 2;

            if (index >= n) {
                index = 1;
            }

            hashMap.put(pair.key, hashMap.get(pair.key) - 1);

            if (hashMap.get(pair.key) ==  0) {
                hashMap.remove(pair.key);
            }
        }

        while (hashMap.size() > 0) {

            for (Character key : hashMap.keySet()) {

                if (hashMap.get(key) > 0) {

                    ans[index] = key;
                    index = index + 2;

                    if (index >= n) {
                        index = 1;
                    }

                    hashMap.put(key, hashMap.get(key) - 1);

                    if (hashMap.get(key) ==  0) {
                        hashMap.remove(key);
                    }

                }

            }

        }

        setTradeLicenseId(ans.toString());
    }

    public boolean noAdjacentCharacters() {

        char[] id = tradeLicenseId.toCharArray();

        for (int i = 0; i < id.length - 1; i++) {

            if (id[i] == id[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
