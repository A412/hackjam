package com.example.chase.mathbash;

import java.util.Scanner;

/**
 Has nothing to do with the MathBash app. This is a console version.
 */
public class ProblemTextUI {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String input = "";

        TimedProblemList problems = new TimedProblemList(10);
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
            try {
                problems.check(input);
            } catch (IllegalArgumentException e) {
                problems.check(-1);
            }
            problems.decrementAll(0.05);
            if (!problems.stillAlive()) {
            System.out.println("A problem timed out!");
            input = "quit";
            }
        }
    }
}
