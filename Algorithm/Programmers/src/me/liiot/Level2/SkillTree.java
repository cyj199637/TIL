package me.liiot.Level2;

/*
스킬트리
 */
public class SkillTree {

    public static void main(String[] args) {

        String[] skillTrees = {"BACDE", "CBADF", "AECB", "BDA"};
        System.out.println(solution("CBD", skillTrees));
    }

    public static int solution(String skill, String[] skillTrees) {

        int count = 0;
        for (String s : skillTrees) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<s.length(); i++) {
                if (skill.indexOf(s.charAt(i)) != -1)
                    sb.append(s.charAt(i));
            }

            if (skill.startsWith(sb.toString()))
                count++;
        }

        return count;
    }
}
