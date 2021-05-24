package ai.us2.codingChallenge;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SamePairCount {


    private static List<FrequencyPair> generateFrequencies(List<SamePairs> samePairs) {

        return samePairs.stream().map(samepair -> FrequencyPair.defaultFreq(samepair.samePairs))
                .collect(Collectors.groupingBy(f -> f.samePair, Collectors.reducing(FrequencyPair::reduce)))
                .values().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

    }
    /*
    Generated a list of all binary sequences using this website : https://numbergenerator.org/numberlist/binary-numbers
    Then Used Dynamic Programming --> Sort of, to find common pairs.

    The logic is :
    Use Initial List of common pair  00-1, 11-> 1, 01 -> 0, 11-> 0 and start building up the Key Value cache.
    this is to avoid redundant transformations

    Then filter out only sequences which are of length 10 and then calculate its frequency.
     */
    private static List<SamePairs> generateAllPairs() {
        URL resource = SamePairCount.class.getClassLoader().getResource("BinaryNumbers.csv");
        try (Stream<String> linesStream = Files.lines(Path.of(resource.toURI()))) {
            linesStream.forEach(SamePairCount::getCommonPairs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return referenceMap.entrySet().stream().filter(entry -> entry.getKey().length() == 10)
                .sorted(Comparator.comparing(Map.Entry::getValue)).map(e -> SamePairs.of(e.getKey(), e.getValue())).collect(Collectors.toList());

    }
    public static Integer getCommonPairs(String sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return 0;
        }
        if (referenceMap.containsKey(sequence)) {
            return referenceMap.get(sequence);
        }
        Integer res = getCommonPairs(sequence.substring(0, 2)) + getCommonPairs(sequence.substring(1));
        referenceMap.put(sequence, res);
        return res;
    }


    public static Map<String, Integer> referenceMap;


    static void setup() {
        referenceMap = new HashMap<>();
        referenceMap.put("00", 1);
        referenceMap.put("11", 1);
        referenceMap.put("10", 0);
        referenceMap.put("01", 0);
    }



    public static void main(String[] args) {
        setup();
        List<SamePairs> samePairs = generateAllPairs();
        System.out.println(samePairs.toString());
        List<FrequencyPair> freq = generateFrequencies(samePairs);
        System.out.println(freq.toString());

    }

    static class SamePairs {
        String binarySequence;
        Integer samePairs;

        static SamePairs of(String binarySequence, Integer samePairs) {
            SamePairs p = new SamePairs();
            p.binarySequence = binarySequence;
            p.samePairs = samePairs;
            return p;
        }

        @Override
        public String toString() {
            return binarySequence + "," + samePairs;
        }

        public static SamePairs merge(SamePairs t, SamePairs t1) {
            t.samePairs += t1.samePairs;
            return t;
        }
    }

    static class FrequencyPair {
        Integer samePair;
        Integer frequency;

        @Override
        public String toString() {
            return samePair + "," + frequency;
        }

        public static FrequencyPair defaultFreq(Integer samePair) {
            FrequencyPair f = new FrequencyPair();
            f.samePair = samePair;
            f.frequency = 1;
            return f;
        }

        public static FrequencyPair reduce(FrequencyPair frequencyPair, FrequencyPair frequencyPair1) {
            frequencyPair.frequency++;
            return frequencyPair;
        }
    }


}