package com.example.chase.mathbash;

import java.util.Scanner;

/**
 Has nothing to do with the MathBash app. This is a console verison.
 */
public class ProblemTextUI {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String input = "";

        ProblemList problems = new ProblemList(10);
        problems.addProblem();
        while(!input.equals("quit")) {
            System.out.print("Completed Problems: ");
            System.out.println(problems.completedProblems());
            System.out.print("Score: ");
            System.out.println(problems.score());
            for (Problem p : problems) {
                System.out.println(p);
            }
            System.out.print("\"quit\" to exit\nAnswer?: ");
            input = s.nextLine();
            problems.check(input);
        }
    }
}
