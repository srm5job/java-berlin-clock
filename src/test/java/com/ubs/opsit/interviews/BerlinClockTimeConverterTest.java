package com.ubs.opsit.interviews;

import static com.ubs.opsit.interviews.BerlinClockTimeConverter.NEWLINE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BerlinClockTimeConverterTest {

	private BerlinClockTimeConverter berlinClock = new BerlinClockTimeConverter();

	@Test(expected = IllegalArgumentException.class)
	public void testInputTimeIsNotNull() {
		berlinClock.validateInput(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputTimeIsNotEmptyString() {
		berlinClock.validateInput("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputTimeFormatIsHHMMSS() {
		berlinClock.validateInput("::");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputTimeValueIsNoNumeric() {
		berlinClock.validateInput("AB:CD:EF");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputHourValueIsL0() {
		berlinClock.validateInput("-1:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputHourValueIsGt24() {
		berlinClock.validateInput("25:00:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputHoursValueIsGt59() {
		berlinClock.validateInput("00:60:00");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInputSecondsValueIsGt59() {
		berlinClock.validateInput("00:00:60");
	}

	@Test
	public void testZeroHours() {
		String expectedOutput = "OOOO" + NEWLINE + "OOOO";
		String output = berlinClock.getHours(0);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void test12Hours() {
		String expectedOutput = "RROO" + NEWLINE + "RROO";
		String output = berlinClock.getHours(12);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void test23Hours() {
		String expectedOutput = "RRRR" + NEWLINE + "RRRO";
		String output = berlinClock.getHours(23);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void test24Hours() {
		String expectedOutput = "RRRR" + NEWLINE + "RRRR";
		String output = berlinClock.getHours(24);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void testZeroMinutes() {
		String expectedOutput = "OOOOOOOOOOO" + NEWLINE + "OOOO";
		String output = berlinClock.getMinutes(0);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void test12Minutes() {
		String expectedOutput = "YYOOOOOOOOO" + NEWLINE + "YYOO";
		String output = berlinClock.getMinutes(12);
		assertThat(output).isEqualTo(expectedOutput);
	}

	@Test
	public void test22Minutes() {
		String expectedOutput = "YYRYOOOOOOO" + NEWLINE + "YYOO";
		String output = berlinClock.getMinutes(22);
		assertThat(output).isEqualTo(expectedOutput);
	}

}
