package com.ll;

public class Calc {
  public static int run(String exp) { // 10 + (10 + 5)    //함수 실행
    exp = exp.trim();   // exp에 exp에 들어있는 문자열의 양옆 공백 제거 후 저장
    exp = stripOuterBracket(exp); // 양 옆 끝의 괄호를 제거하기 위한 함수를 실행하고 반환한 값을 exp에 저장

    // 연산기호가 없으면 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp); // 양 옆 공백이 없으면 정수형으로 형변환 후 exp값을 반환

    boolean needToMultiply = exp.contains(" * ");   // exp에 * 기호가 있으면 트루 저장
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");  // exp에 + 기호나 - 기호가 있으면 트루 저장

    boolean needToCompound = needToMultiply && needToPlus;  // 둘다 참 일때 트루 저장
    boolean needToSplit = exp.contains("(") || exp.contains(")"); // 괄호가 하나라도 있을때 트루 저장

    if (needToSplit) {  // 참이면 실행

      int splitPointIndex = findSplitPointIndex(exp); // 원하는 문자를 찾는 함수 실행한 후 반환값을 변수에 저장

      String firstExp = exp.substring(0, splitPointIndex); // 인덱스 0번부터 찾은 문자 전까지 쪼개서 변수(firstExp)에 저장
      String secondExp = exp.substring(splitPointIndex + 1);  // 찾은 문자 후부터 끝까지 쪼개서 변수(secondExp)에 저장

      char operator = exp.charAt(splitPointIndex);  // 찾은 문자를 변수에 저장

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp);  // 괄호안에 있는 연산식 계산 후 나온 값을 반환하고 괄호를 없애주는 역할

      return Calc.run(exp); // 괄호없는상태의 문자열 exp를 함수에 넣어서 실행

    } else if (needToCompound) {  // + 와 * 기호가 둘다 있을때 실행
      String[] bits = exp.split(" \\+ ");

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // TODO
    }
    if (needToPlus) { // + 와 - 기호중 하나라도 있을때 실행

      exp = exp.replaceAll("\\- ", "\\+ \\-"); // 10 + 10 - 5 <-> 10 + 10 + -5 로 바꾸기 위한 코드

      String[] bits = exp.split(" \\+ "); // + 기호를 기준으로 쪼개서 배열에 저장

      int sum = 0;

      for (int i = 0; i < bits.length; i++) { //연산식의 모든값을 더해서 저장 후 반환
        sum += Integer.parseInt(bits[i]);
      }

      return sum;
    } else if (needToMultiply) {  // * 기호만 있을때 실행
      String[] bits = exp.split(" \\* "); // * 기호를 기준으로 쪼개서 배열에 저장

      int rs = 1;

      for (int i = 0; i < bits.length; i++) { //연산식의 모든값을 곱해서 저장 후 반환
        rs *= Integer.parseInt(bits[i]);
      }
      return rs;
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다");
  }

  private static int findSplitPointIndexBy(String exp, char findChar) {  // 원하는 문자의 인덱스를 찾는 함수
    int bracketCount = 0;

    for (int i = 0; i < exp.length(); i++) {  //i는 0이고 i가 문자열 exp의 길이미만일동안 반복
      char c = exp.charAt(i); // 인자로 받은 findChar에 맞는 문자가 나올때 i값을 반환하는 함수

      if (c == '(') {
        bracketCount++;
      } else if (c == ')') {
        bracketCount--;
      } else if (c == findChar) {
        if (bracketCount == 0) return i;
      }
    }
    return -1;
  }

  private static int findSplitPointIndex(String exp) {  // 10 + (10 + 5)
    int index = findSplitPointIndexBy(exp, '+');  //함수에 exp를 넣고 + 기호를 찾고 나온 반환값을 변수에 저장

    if (index >= 0) return index;   // + 기호를 찾으면 인덱스를 반환

    return findSplitPointIndexBy(exp, '*'); // 함수에 exp를 넣고 * 기호를 찾고 나온 반환값 반환
  }

  private static String stripOuterBracket(String exp) { //문자열 인자가 담긴 함수 실행
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    } //exp의 첫번째 문자가 괄호이면서 마지막 문자도 괄호면 실행
    //charAt으로 세는 인덱스는 0부터이기 때문에 마지막 문자를 뽑기위해 -1을 넣음


    if (outerBracketCount == 0) return exp;


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}