package org.example.algorithm;

public class Leetcode67 {
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length()-1,j = b.length()-1,x=0, y=0, current=0, carry = 0;
        while(i>=0 || j>=0 || carry !=0){
            x = i>=0 ? a.charAt(i--)-'0' : 0;
            y = j>=0 ? b.charAt(j--)-'0' : 0;
            current = (x + y + carry)%2;
            carry = (x + y + carry)/2;
            ans.append(String.valueOf(current));
        }
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        String a,b;
        a = "1010";
        b = "1011";
        System.out.println(addBinary(a, b));
    }
}
