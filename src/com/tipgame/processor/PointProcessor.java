package com.tipgame.processor;

public class PointProcessor {
	private Integer homeTeamFinal;
	private Integer awayTeamFinal;
	private Integer homeTeamTipp;
	private Integer awayTeamTipp;
	
	public PointProcessor(Integer homeTeamFinal, Integer awayTeamFinal,
			Integer homeTeamTipp, Integer awayTeamTipp)
	{
		this.homeTeamFinal = homeTeamFinal;
		this.homeTeamTipp = homeTeamTipp;
		this.awayTeamFinal = awayTeamFinal;
		this.awayTeamTipp = awayTeamTipp;
	}
	
	public Integer computePoints()
	{
		Integer points = 0;
		if (isExcactPrediction())
			points = 6;
		else if (isGoalRatio())
			points = 4;
		else if (isTendency())
			points = 2;
		else if (isResultOfScoredGoals())
			points = 1;
		
		return points;
	}
	
	private Boolean isResultOfScoredGoals()
	{
		return ((homeTeamTipp+awayTeamTipp) == (homeTeamFinal+awayTeamFinal));
	}
	
	private Boolean isTendency()
	{
		return (homeTeamTipp > awayTeamTipp) == (homeTeamFinal > awayTeamFinal);
	}
	
	private Boolean isGoalRatio()
	{
		return (homeTeamTipp - awayTeamTipp) == (homeTeamFinal - awayTeamFinal);
	}
	
	private Boolean isExcactPrediction()
	{
		return (homeTeamFinal == homeTeamTipp) && (awayTeamFinal == awayTeamTipp);
	}

}
