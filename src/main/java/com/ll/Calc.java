package com.ll;

public class Calc {
  public static int run(String exp) {

    exp = exp.replaceAll("\\- ", "\\+ \\-");

    String[] bits = exp.split(" \\+ ");

    int sum = 0;

    for (int i = 0; i < bits.length; i++) {
      int a = Integer.parseInt(bits[i]);
      sum += a;
    }

//    int i = 0;
//    while (i < bits.length) {
//      int a = Integer.parseInt(bits[i]);
//      sum += a;
//      i++;
//    }

    return sum;

//    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }
}