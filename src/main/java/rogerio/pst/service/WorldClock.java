package rogerio.pst.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldClock {
	private String  $id;
	private String currentDateTime;
	private String utcOffset;
	private boolean isDayLightSavingsTime;
	private String dayOfTheWeek;
	private String timeZoneName;
	private Long currentFileTime;
	private String ordinalDate;
	private String serviceResponse;	
}
