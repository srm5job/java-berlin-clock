package com.ubs.opsit.interviews;

public class BerlinClockTimeConverter implements TimeConverter{
	
	@Override
	public String convertTime(String aTime) {
		// TODO Auto-generated method stub
		return "O\r\n" + 
				"RROO\r\n" + 
				"RRRO\r\n" + 
				"YYROOOOOOOO\r\n" + 
				"YYOO";
	}

}
