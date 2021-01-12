package me.liiot.Graph;

import java.util.*;

/*
33) Word Ladder
두 단어(beginWord, endWord)와 사전의 단어 리스트가 주어졌을 때, beginWord에서 endWord까지의 가장 짧은 변환 시퀀스의 길이를 찾으세요.
한 번에 하나의 문자만 변경할 수 있습니다.
변형된 각 단어는 단어 리스트에 있어야 합니다.
beginWord는 변형된 단어가 아닙니다.

참고 :
알맞은 변환 시퀀스가 없으면 0을 반환합니다.
모든 단어의 길이는 같습니다.
모든 단어에는 소문자 알파벳만 포함됩니다.
단어 리스트에 중복은 없습니다.
beginWord와 endWord가 비어 있지 않고 동일하지 않습니다.

1. 탐색할 언어를 큐에서 꺼낸다.
2. 한 번에 하나의 문자만 변경할 수 있으므로 단어를 쪼개서 한 문자씩 a~z까지 바꾸어 본다.
3. 바꾼 단어가 사전에 있는 단어인지 체크한다.
4. 사전에 있는 단어라면 사전에서 지우고, 다음 탐색 대상으로써 큐에 저장한다.
 */
public class WordLadder {

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solve(beginWord, endWord, wordList));
    }

    private static int solve(String beginWord, String endWord, String[] wordList) {

        Queue<String> queue = new LinkedList<>();
        Set<String> dict = new HashSet<>(Arrays.asList(wordList));
        queue.add(beginWord);
        dict.remove(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                String word = queue.poll();

                if (word.equals(endWord))   // String은 ==가 아니라 equals()
                    return level;

                neighbor(word, queue, dict);
            }
            level++;
        }

        return 0;
    }

    private static void neighbor(String word, Queue<String> queue, Set<String> dict) {

        for (int i=0; i<word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char c='a'; c<='z'; c++) {
                chars[i] = c;
                String newWord = String.valueOf(chars);
                if (dict.remove(newWord)) { // 사전에서 단어를 지우지 않으면 이미 탐색된 단어가 또다시 탐색 대상이 될 수 있음
                    queue.add(newWord);     // 조건에 알맞은 단어를 찾을 때마다 탐색 대상으로 선정
                }
            }
        }
    }
}
