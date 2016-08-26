package com.stanson.dev;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;

public class AnagramsTest extends TestCase {

    @Test
    public void test() {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("set");
        dictionary.add("tes");
        Anagrams anagrams = new Anagrams(dictionary);
        Set<String> perms = anagrams.permutation("test");
        assertNotNull(perms);
        assertEquals("[ts, tse, test, tste, set, estt, te, est, tst, stte, sett, st, etts, ste, etst, tset, stet, tets, tte, tts, ttse, t, ets, tt, ett, stt, et, tet, tes, ttes]", perms.toString());
        Set<String> anagramSet = anagrams.anagrams("test");
        assertNotNull(anagramSet);
        assertEquals("[set, tes]", anagramSet.toString());
    }
}
