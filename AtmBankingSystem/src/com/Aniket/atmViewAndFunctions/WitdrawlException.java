package com.Aniket.atmViewAndFunctions;

public class WitdrawlException extends Exception{
		
	protected final boolean shortOrNot;
	WitdrawlException(final long shortAmount){
		super("ERROR : you are short of "+shortAmount+", can't be withdrawled");
		this.shortOrNot = true;
	}
}
