package com.ubs.opsit.interviews;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BerlinClockTimeConverter implements TimeConverter {

	private static final Logger LOG = LoggerFactory.getLogger(BerlinClockTimeConverter.class);

	public static String NEWLINE = System.getProperty("line.separator");

	public static void main(String[] args) {

		TimeConverter timeConverter = new BerlinClockTimeConverter();

		LOG.info("Berlin Clock = " + NEWLINE
				+ timeConverter.convertTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString()));

	}

	void validateInput(String time) {

		if ((null == time) || time.isEmpty())
			throw new IllegalArgumentException("Time value can not be null or empty");

		if (time.split(":").length != 3)
			throw new IllegalArgumentException("Please provide time in format HH:mm:ss");

		String[] tcomponents = time.split(":");
		int hours, minutes, seconds = 0;
		try {
			hours = Integer.parseInt(tcomponents[0]);
			minutes = Integer.parseInt(tcomponents[1]);
			seconds = Integer.parseInt(tcomponents[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Time value must be numeric");
		}

		if (hours < 0 || hours > 24)
			throw new IllegalArgumentException("Hours value must be between 0 and 24");

		if (minutes < 0 || minutes > 59)
			throw new IllegalArgumentException("Minutes value must be between 0 and 59");

		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("Seconds value must be between 0 and 59");
	}

	@Override
	public String convertTime(String aTime) {

		validateInput(aTime);
		
		String[] tcomponents = aTime.split(":");
		int hours, minutes, seconds = 0;
		try {
			hours = Integer.parseInt(tcomponents[0]);
			minutes = Integer.parseInt(tcomponents[1]);
			seconds = Integer.parseInt(tcomponents[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Time value must be numeric");
		}

		// LocalTime time = LocalTime.parse(aTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

		// int hours = time.getHour();
		// int minutes = time.getMinute();
		// int seconds = time.getSecond();

		String bSeconds = getSeconds(seconds);
		String bHours = getHours(hours);
		String bMinutes = getMinutes(minutes);

		String output = bSeconds + NEWLINE + bHours + NEWLINE + bMinutes;

		return output;
	}

	/**
	 * On the top of the clock there is a yellow lamp that blinks on/off every two
	 * seconds.
	 * 
	 * @param seconds
	 * @return
	 */
	String getSeconds(int seconds) {
		return seconds % 2 == 0 ? "Y" : "O";
	}

	/**
	 * The top two rows of lamps are red. These indicate the hours of a day. In the
	 * top row there are 4 red lamps. Every lamp represents 5 hours. In the lower
	 * row of red lamps every lamp represents 1 hour. So if two lamps of the first
	 * row and three of the second row are switched on that indicates 5+5+3=13h or 1
	 * pm.
	 * 
	 * @param hours
	 * @return
	 */
	String getHours(int hours) {

		int topHoursLightsOn = hours / 5;
		int bottomHoursLightsOn = hours % 5;

		String topLightBulbs = turnLightBulbsOnOff(4, topHoursLightsOn, "R");
		String bottomLightBulbs = turnLightBulbsOnOff(4, bottomHoursLightsOn, "R");

		String output = topLightBulbs + NEWLINE + bottomLightBulbs;

		return output;
	}

	/**
	 * The two rows of lamps at the bottom count the minutes. The first of these
	 * rows has 11 lamps, the second 4. In the first row every lamp represents 5
	 * minutes. In this first row the 3rd, 6th and 9th lamp are red and indicate the
	 * first quarter, half and last quarter of an hour. The other lamps are yellow.
	 * In the last row with 4 lamps every lamp represents 1 minute.
	 * 
	 * @param minutes
	 * @return
	 */
	String getMinutes(int minutes) {

		int topMinutesLightsOn = minutes / 5;
		int bottomMinutesLightsOn = minutes % 5;

		String topLightBulbs = turnLightBulbsOnOff(11, topMinutesLightsOn, "Y");
		String bottomLightBulbs = turnLightBulbsOnOff(4, bottomMinutesLightsOn, "Y");

		// In this first row the 3rd, 6th and 9th lamp are red and indicate the first
		// quarter, half and last quarter of an hour
		topLightBulbs = topLightBulbs.replaceAll("YYY", "YYR");

		String output = topLightBulbs + NEWLINE + bottomLightBulbs;

		return output;
	}

	/**
	 * Turn Light Bulbs ON or OFF
	 * 
	 * @param numberOfLightBulbs
	 * @param onLightBulbs
	 * @param lightColor
	 * @return
	 */
	private String turnLightBulbsOnOff(int numberOfLightBulbs, int onLightBulbs, String lightColor) {
		int offLightBulbs = numberOfLightBulbs - onLightBulbs;
		String on = String.join("", Collections.nCopies(onLightBulbs, lightColor));
		String off = String.join("", Collections.nCopies(offLightBulbs, "O"));
		return on + off;
	}

}
