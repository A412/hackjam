package com.example.chase.mathbash;

public class Problem {
  private String question;
  private int answer;
  public Problem(String q, int a) {
    question = q;
    answer = a;
  }

  public Problem(String q, String a) {
    question = q;
    try {
      answer = Integer.parseInt(a);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }

  public boolean checkAnswer(Number ans) {
    try {
      return checkAnswer(ans.intValue());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }

  public boolean checkAnswer(int ans) {
    return (answer == ans);
  }

  public static Problem generate() {
    String operation;
    int ans;
    int first = (int)(10 * Math.random());
    int second = (int)(10 * Math.random());
    if (Math.random() > 0.5) {
      operation = " + ";
      ans = first + second;
    } else {
      operation = " * ";
      ans = first * second;
    }
    return new Problem(first + operation + second, ans);
  }

  @Override
  public String toString() {
    return question;
  }
}
