package pl.joannaszczesna;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

  @Test
  void exampleInputFileNotExist_findTotalDistance_returnException() throws URISyntaxException {
    String notExistFileName = "notExist.txt";
    URL resource = getClass().getClassLoader().getResource(notExistFileName);

    Exception exception = assertThrows(NullPointerException.class, () -> {
      String path = Paths.get(resource.toURI()).toString();
    });
    String expectedMessage = "is null";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void part1_sample() throws URISyntaxException, IOException {
    Integer totalDistance = Day01.countTotalDistance(readInput("day01_sample"));

    assertEquals(11, totalDistance);
  }


  @Test
  void part1_input() throws URISyntaxException, IOException {
    Integer totalDistance = Day01.countTotalDistance(readInput("day01_input"));

    assertEquals(2176849, totalDistance);
  }

  @Test
  void part2_sample() throws URISyntaxException, IOException {
    Long similarityScore = Day01.countSimilarityScore(readInput("day01_sample"));

    assertEquals(31L, similarityScore);
  }

  @Test
  void part2_input() throws URISyntaxException, IOException {
    Long similarityScore = Day01.countSimilarityScore(readInput("day01_input"));

    assertEquals(23384288, similarityScore);
  }

  private List<String> readInput(String fileName) throws URISyntaxException, IOException {
    return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
  }
}

