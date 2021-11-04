package test;

import java.util.*;

public class SecretSanta
{
	static Scanner scanner;
	static int count;
	static String[] input;
	static List<String> stringParticipantsList;
	static List<Participant> participantList;
	static Random random;
	static int randomNumber;

	static int index;
	static int maxCount;
	static int left;

	public static void main(String[] args)
	{
		input = new String[]{"disco", "nerm", "pyro", "null", "scorpio"};
		stringParticipantsList = new ArrayList<>();
		participantList = new ArrayList<>();
		random = new Random();
		populateParticipantList();
		wantsToAvoid("scorpio", "disco", "null");

		print(participantList);
		Collections.sort(participantList, new PeopleAvoidanceComparator());
		print(participantList);
		left = participantList.size();
		for (Participant p : participantList)
		{
			while (p.getSecretSantaFor() == "")
			{
				randomNumber = random.nextInt(stringParticipantsList.size());
				Participant matched = participantList.get(randomNumber);
				if (p.getName() != matched.getName())
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
						print("Force close this program and run it again. Can I implement something to deal with this? yes, will I? no.");
						return;
					}
				}

			}
		}

	}


	private static void reOrderList()
	{


	}

	private static void wantsToAvoid(String name, String... args)
	{
		for (Participant p : participantList)
		{
			if (name == p.getName())
			{
				p.setPeopleToAvoid(List.of(args));
			}
		}

		for (Participant pa : participantList)
		{
			for (String arg : args)
			{
				if (arg == pa.getName())
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
	public int compare(Participant p1, Participant p2)
	{
		int returnValue = 0;
		int p1AvoidSize = p1.getPeopleToAvoid().size();
		int p2AvoidSize = p2.getPeopleToAvoid().size();
		if(p1AvoidSize < p2AvoidSize)
			returnValue = -1;
		else
			if(p1AvoidSize == p2AvoidSize)
				returnValue = 0;
			else
				returnValue = 1;

		System.out.print(p1.getName() + " " + p1.getPeopleToAvoid().size() + " TO ");
		System.out.println(p2.getName() + " " + p2.getPeopleToAvoid().size());
		System.out.println(p1.getPeopleToAvoid().size() > p2.getPeopleToAvoid().size() ? p1.getPeopleToAvoid().size() : p1.getPeopleToAvoid().size() == p2.getPeopleToAvoid().size() ? p1.getPeopleToAvoid().size() : p2.getPeopleToAvoid().size());
		return p2AvoidSize - p1AvoidSize;
	}

}
