package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!isNumberValid(tradeLicenseId)) {
            String rearrangedId = arrangeString(tradeLicenseId);
            if(rearrangedId == "") {
                throw new Exception("Valid License can not be generated");
            }
            else this.tradeLicenseId = rearrangedId;
        }
    }
    public String arrangeString(String s) {
        int n = s.length();
        int arr[] = new int[26];

        for(char ch : s.toCharArray()) {
            arr[(int)ch + (int)'A']++;
        }

        char ch_max = getMaxFreqChar(arr);
        int maxFreq = arr[(int)ch_max + (int)'A'];

        if(n % 2 == 0) {
            if(maxFreq > (n/2) + 1) return "";
            else if(maxFreq > (n/2) + 2) return "";
        }

        char ans[] = new char[n];
        int idx = 0;
        for(idx=0; idx<n; idx+=2) {
            if(arr[ch_max] > 0) {
                ans[idx] = ch_max;
                arr[ch_max]--;
            }
            else break;
        }
        for(int i=0; i<26; i++) {
            char ch = (char) ('A' + i);
            while(arr[ch] > 0) {
                if(idx >= n) {
                    idx = 1;
                }
                ans[idx] = ch;
                idx = idx + 2;
                arr[ch]--;
            }
        }
        String res = "";
        for(int i=0; i<n; i++) {
            res += ' ';
        }
        int ind = 0;
        while(maxFreq > 0) {
            res = res.substring(0, ind) + ch_max + res.substring(ind+1);
            ind = ind + 2;
            maxFreq--;
        }
        arr[(int)ch_max - (int)'A'] = 0;
        for(int i=0; i<26; i++) {
            while(arr[i] > 0) {
                ind = (ind >= n) ? 1 : ind;
                res = res.substring(0, ind) + (char) ((int)'A' + i) + res.substring(ind+1);
                ind += 2;
                arr[i]--;
            }
        }
        return res;
    }

    private char getMaxFreqChar(int[] arr) {
        int max = 0;
        char ch = 0;
        for(int i=0; i<26; i++) {
            if(arr[i] > max) {
                max = arr[i];
                ch = (char) (i + (int)'A');
            }
        }
        return ch;
    }
    public boolean isNumberValid(String licenseId) {
        for(int i=0; i<licenseId.length()-1; i++) {
            if(licenseId.charAt(i) == licenseId.charAt(i+1)) return false;
        }
        return true;
    }
    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
