package com.stanson.dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Sets;

public class Anagrams {

    private Set<String> dictionary;

    public Anagrams(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public Set<String> permutation(String s) {
        Set<String> res = new HashSet<String>();
        if (s.length() == 1) {
            res.add(s);
        } else if (s.length() > 1) {
            int lastIndex = s.length() - 1;
            Set<String> perms = permutation(s.substring(0, lastIndex));
            res = merge(perms, s.substring(lastIndex));
            res.addAll(perms);
        }
        return res;
    }

    public Set<String> merge(Set<String> list, String c) {
        Set<String> res = new HashSet<String>();
        for (String s : list) {
            for (int i = 0; i <= s.length(); ++i) {
                String ps = new StringBuffer(s).insert(i, c).toString();
                res.add(ps);
            }
        }
        return res;
    }

    private static Set<String> getDictionary() throws FileNotFoundException {
        ClassLoader classLoader = Anagrams.class.getClassLoader();
        File file = new File(classLoader.getResource("google-10k-actual-words.txt").getFile());
        Set<String> set = new HashSet<String>();
        Scanner s = null;
        try {
            s = new Scanner(file);
            while (s.hasNext()) {
                set.add(s.next().toLowerCase());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return set;
    }

    public Set<String> anagrams(String str) {
        return Sets.intersection(permutation(str), dictionary);
    }

    public static void main(String[] args) throws Exception {
        Set<String> dictionary = getDictionary();
        Anagrams anagrams = new Anagrams(dictionary);

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Input word to produce anagrams.");
            String input = in.nextLine();
            if ("q".equals(input)) {
                System.out.println("Exit!");
                in.close();
                System.exit(0);
            }

            Set<String> words = anagrams.anagrams(input);
            for (String word : words) {
                System.out.println(word);
            }

        }

    }
}
