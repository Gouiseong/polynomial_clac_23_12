package com.ll;

public class Calc {
  public static int run(String exp) {

    int sum;
    String[] bits=exp.split(" \\+");
    if(bits.length==2){
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      sum=a+b;
    }

    String[] bit1=exp.split("-");
    if(bit1.length==2){
      int a = Integer.parseInt(bit1[0]);
      int b = Integer.parseInt(bit1[1]);
      sum=a-b;
    }

    return sum;
  }
}