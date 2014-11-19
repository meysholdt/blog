package tests;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest {

	@Parameters(name = "number: {0}")
	public static Iterable<Object[]> parameters() {
		return Arrays.asList(new Object[] { 6 }, new Object[] { 7 });
	}

	private final int val;

	public ParameterizedTest(int val) {
		this.val = val;
	}

	@Test
	public void divByThree() {
		if (this.val % 3 != 0)
			throw new RuntimeException();
	}

	@Test
	public void divByTwo() {
		Assert.assertEquals(0, this.val % 2);
	}
}
