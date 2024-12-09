package pl.joannaszczesna;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

  @Test
  void part1_sample() throws URISyntaxException, IOException {
    long result = Day03.sumCorruptedMemory(readInput("day03_sample"));

    assertEquals(161, result);
  }

  @Test
  void part1_input() throws URISyntaxException, IOException {
    long result = Day03.sumCorruptedMemory(readInput("day03_input"));

    assertEquals(183788984, result);
  }

  @Test
  void part1_singleSequence() {
   String sample = "mul ( 2 , 4 )";
    String sample2 = "mul(4*, mul(6,9!, ?(12,34)";
    long result = Day03.sumCorruptedMemory(sample);
    long result2 = Day03.sumCorruptedMemory(sample2);

    assertEquals(0, result);
    assertEquals(0, result2);
  }

  @Test
  void part2_input() {
    String sample = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
    long result = Day03.sumWithDoInstruction(sample);

    assertEquals(48, result);
  }

  private String readInput(String fileName) throws URISyntaxException, IOException {
    return Files.readString(Paths.get(getClass()
      .getClassLoader()
      .getResource(fileName)
      .toURI()));
  }
}
