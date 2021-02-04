package me.liiot.Level1;

import java.util.Arrays;

/*
다트 게임
 */
public class DartGame {

    public static void main(String[] args) {

        String s1 = "1S2D*3T";
        System.out.println(solution(s1));

        String s2 = "1D2S#10S";
        System.out.println(solution(s2));

        String s3 = "1D2S3T*";
        System.out.println(solution(s3));
    }

    public static int solution(String s) {

        String[] scores = s.split("[^0-9]+");
        String[] bonuses = s.split("[0-9]+");
        int[] finalScores = new int[3];

        for (int i=0; i<3; i++) {
            int score = Integer.valueOf(scores[i]);
            String bonus = bonuses[i+1];

            if (bonus.startsWith("D")) {
                score = (int) Math.pow(score, 2);
            } else if (bonus.startsWith("T")) {
                score = (int) Math.pow(score, 3);
            }

            if (bonus.endsWith("*")) {
                score *= 2;
                if (i != 0)
                    finalScores[i-1] *= 2;
            } else if (bonus.endsWith("#")) {
                score *= -1;
            }

            finalScores[i] = score;
        }

        return Arrays.stream(finalScores).sum();
    }
}
