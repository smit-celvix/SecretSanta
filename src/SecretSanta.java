/*A secret santa custom program developed by yours truely johntheripper only for saskatoon discord group and beloved technical pyro (Sean).
*
* Sorry Sean I can't participate this time but this would be my gift to you this year.
*
* I can probably fix this and add more functions but I don't have much brain power for it.
*
* Developed by johntheripp3r for Technical花火#8729
*
* Date: Nov 3rd 2021.
*
* */


import java.util.*;

public class SecretSanta
{
	// Variable declaration, you probably don't need to worry about it
	static String[] input;
	static List<String> stringParticipantsList;
	static List<Participant> participantList;
	static Random random;
	static int randomNumber;

	static int left;

	public static void main(String[] args)
	{
		// Insert the name of the peoples here
		// Make sure you get the spelling right everytime you trynna add someone here, otherwise it wont work
		input = new String[]{"disco", "nerm", "pyro", "null", "scorpio", "crypto", "greg", "rabidfox", "awhilesbrook", "bergo", "james", "raevb", "l-rae", "daniell"};
		stringParticipantsList = new ArrayList<>();
		participantList = new ArrayList<>();
		random = new Random();
		populateParticipantList();

		// Enter the list of people who you don't want giving gifts to each other, if there are many exceptions and a short list,
		// this program will fail and you'll have to run it again.
		// Who wants to avoid whom, you get the point, example below
		wantsToAvoid("scorpio", "disco");
		// Also you can do as below:
		// wantsToAvoid("scorpio", "disco", "null", "nerm");


		//print(participantList);
		Collections.sort(participantList, new PeopleAvoidanceComparator());
		//print(participantList);
		left = participantList.size();
		for (Participant p : participantList)
		{
			while (p.getSecretSantaFor().equals(""))
			{
				randomNumber = random.nextInt(stringParticipantsList.size());
				Participant matched = participantList.get(randomNumber);
				if (!p.getName().equals(matched.getName()))
				{
					if (!matched.isTaken())
					{
						if (!p.getPeopleToAvoid().contains(matched.getName()))
						{
							p.setSecretSantaFor(matched.getName());
							matched.setTaken(true);
							print(p.getName() + " is secret santa for " + matched.getName());
							left--;
						}
					}
				}
				else
				{
					if(left == 1)
					{
						print("It's an infinite loop as " + p.getName() + " is the only one left for " + matched.getName());
						print("Force close this program and run it again. Can I implement something to deal with this? yes, will I? no. May be...");
						return;
					}
				}

			}
		}

	}

	private static void wantsToAvoid(String name, String... args)
	{
		for (Participant p : participantList)
		{
			if (name.equals(p.getName()))
			{
				p.setPeopleToAvoid(List.of(args));
			}
		}

		for (Participant pa : participantList)
		{
			for (String arg : args)
			{
				if (arg.equals(pa.getName()))
				{
					pa.setPeopleToAvoid(List.of(name));
				}
			}
		}
	}

	private static void populateParticipantList()
	{
		for (String name : input)
		{
			stringParticipantsList.add(name);
			participantList.add(new Participant(name));
		}
	}

	private static void print(Object o)
	{
		System.out.println(o);
	}
}

class PeopleAvoidanceComparator implements Comparator<Participant>
{
	@Override
	public int compare(Participant participant1, Participant participant2)
	{
		int p1AvoidSize = participant1.getPeopleToAvoid().size();
		int p2AvoidSize = participant2.getPeopleToAvoid().size();
		return p2AvoidSize - p1AvoidSize;
	}

}
class Participant
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
