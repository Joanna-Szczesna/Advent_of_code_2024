package pl.joannaszczesna;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

/*
Day 01
--- Part One ---
For example:

3   4
4   3
2   5
1   3
3   9
3   3
Maybe the lists are only off by a small amount! To find out, pair up the report and measure how far apart they are.
 Pair up the smallest number in the left list with the smallest number in the right list,
  then the second-smallest left number with the second-smallest right number, and so on.

Within each pair, figure out how far apart the two report are; you'll need to add up all of those distances.
 For example, if you pair up a 3 from the left list with a 7 from the right list,
  the distance apart is 4; if you pair up a 9 with a 3, the distance apart is 6.

In the example list above, the pairs and distances would be as follows:

The smallest number in the left list is 1, and the smallest number in the right list is 3.
 The distance between them is 2.
The second-smallest number in the left list is 2, and the second-smallest number in the right list is another 3.
 The distance between them is 1.
The third-smallest number in both lists is 3, so the distance between them is 0.
The next report to pair up are 3 and 4, a distance of 1.
The fifth-smallest report in each list are 3 and 5, a distance of 2.
Finally, the largest number in the left list is 4, while the largest number in the right list is 9;these are a distance 5 apart.
To find the total distance between the left list and the right list, add up the distances between all of the pairs you found.
 In the example above, this is 2 + 1 + 0 + 1 + 2 + 5, a total distance of 11!

Your actual left and right lists contain many location IDs. What is the total distance between your lists?

--- Part Two ---
Your analysis only confirmed what everyone feared: the two lists of location IDs are indeed very different.

Or are they?

The Historians can't agree on which group made the mistakes or how to read most of the Chief's handwriting,
 but in the commotion you notice an interesting detail: a lot of location IDs appear in both lists!
  Maybe the other report aren't location IDs at all but rather misinterpreted handwriting.

This time, you'll need to figure out exactly how often each number from the left list appears in the right list.
 Calculate a total similarity score by adding up each number in the left list after multiplying it
  by the number of times that number appears in the right list.

Here are the same example lists again:

3   4
4   3
2   5
1   3
3   9
3   3
For these example lists, here is the process of finding the similarity score:

The first number in the left list is 3. It appears in the right list three times, so the similarity score increases by 3 * 3 = 9.
The second number in the left list is 4. It appears in the right list once, so the similarity score increases by 4 * 1 = 4.
The third number in the left list is 2. It does not appear in the right list, so the similarity score does not increase (2 * 0 = 0).
The fourth number, 1, also does not appear in the right list.
The fifth number, 3, appears in the right list three times; the similarity score increases by 9.
The last number, 3, appears in the right list three times; the similarity score again increases by 9.
So, for these example lists, the similarity score at the end of this process is 31 (9 + 4 + 0 + 0 + 9 + 9).

*/
class Day01 {

  static Integer countTotalDistance(List<String> inputLines) {
    List<Coordinate> coordinates = getCoordinates(inputLines);

    List<Integer> sortedLeft = coordinates.stream().map(c -> c.left).sorted().toList();
    List<Integer> sortedRight = coordinates.stream().map(c -> c.right).sorted().toList();

    return IntStream.range(0, sortedRight.size())
      .map(i -> Math.abs(sortedRight.get(i) - sortedLeft.get(i))
      ).sum();
  }

  static Long countSimilarityScore(List<String> inputLines) {
    List<Coordinate> coordinates = getCoordinates(inputLines);

    List<Integer> left = coordinates.stream().map(c -> c.left).toList();
    List<Integer> right = coordinates.stream().map(c -> c.right).toList();
    Map<Integer, Long> leftCounts = left.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
    Map<Integer, Long> rightCounts = right.stream().collect(groupingBy(Function.identity(), Collectors.counting()));

    return leftCounts.entrySet().stream()
        .filter(entry ->  rightCounts.containsKey(entry.getKey()))
          .map(e -> e.getKey()* e.getValue() * rightCounts.get(e.getKey()))
      .reduce(0L, Long::sum);
  }

  private static List<Coordinate> getCoordinates(List<String> inputLines) {
    return inputLines.stream()
      .map(line -> line.split("\\s+", 2))
      .map(a -> new Coordinate(Integer.parseInt(a[0]), Integer.parseInt(a[1])))
      .toList();
  }
  record Coordinate(Integer left, Integer right) {
  }
}

