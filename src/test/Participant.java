package test;

import java.util.ArrayList;
import java.util.List;

public class Participant
{
	private  String name;
	private  List<String> peopleToAvoid;
	private String secretSantaFor;
	private boolean isTaken;


	public Participant(String name)
	{
		this.name = name;
		this.peopleToAvoid = new ArrayList<>();
		this.secretSantaFor = "";
		this.isTaken = false;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<String> getPeopleToAvoid()
	{
		return peopleToAvoid;
	}

	public void setPeopleToAvoid(List<String> peopleToAvoid)
	{
		this.peopleToAvoid = peopleToAvoid;
	}

	public String getSecretSantaFor()
	{
		return secretSantaFor;
	}

	public void setSecretSantaFor(String secretSantaFor)
	{
		this.secretSantaFor = secretSantaFor;
	}

	public boolean isTaken()
	{
		return isTaken;
	}

	public void setTaken(boolean taken)
	{
		isTaken = taken;
	}

	@Override
	public String toString()
	{
		return getName() + " is secret santa for " + getSecretSantaFor();
	}
}
