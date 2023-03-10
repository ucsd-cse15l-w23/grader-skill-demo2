import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {

  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "c", "e");
    List<String> right = Arrays.asList("b", "h");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c", "e", "h");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeLeftEnd() {
    List<String> left = Arrays.asList("a", "c", "z");
    List<String> right = Arrays.asList("b", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c", "d", "z");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilterSingle() {
    List<String> input = Arrays.asList("Moon", "MOO", "moo");
    List<String> expect = Arrays.asList("Moon");
    List<String> filtered = ListExamples.filter(input, new IsMoon());
    assertEquals(expect, filtered);
  }

  @Test(timeout = 500)
  public void testFilterMulti() {
    List<String> input = Arrays.asList("Moon", "MOO", "moon", "MOON");
    List<String> expect = Arrays.asList("Moon", "moon", "MOON");
    List<String> filtered = ListExamples.filter(input, new IsMoon());
    assertEquals(expect, filtered);
  }
}
