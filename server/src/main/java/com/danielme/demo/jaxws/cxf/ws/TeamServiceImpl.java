package com.danielme.demo.jaxws.cxf.ws;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.jws.WebMethod;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.danielme.demo.jaxws.cxf.model.Player;

@Service(value="teamService")
public class TeamServiceImpl implements ITeamService 
{
	
	public static final Logger logger = Logger.getLogger(TeamServiceImpl.class);
	 
	private List<Player> team = null;	
	

	public TeamServiceImpl() 
	{
	    team = new LinkedList<Player>();
	    team.add(new Player(1, "David De Gea", 22));
	    team.add(new Player(2, "Rafael", 22));
	    team.add(new Player(3, "Patrice Evra", 31));
	    team.add(new Player(4, "Phil Jones", 21));
	    team.add(new Player(5, "Rio Ferdinand", 34));
	    team.add(new Player(7, "Antonio Valencia", 27));
	    team.add(new Player(8, "Anderson", 24));
	    team.add(new Player(10, "Wayne Rooney", 27));	    
	}
	
	@Override
	public List<Player> getTeam() 
	{
		logger.info("team requested");
		return team;
	}

	@Override
	public List<Player> getPlayers(int...numbers) 
	{
		List<Player> result = new LinkedList<Player>();
		
		if (numbers != null)
		{
			Player player = null;
			for (int i =0; i< numbers.length;i++)
			{
				player = findById(numbers[i]);
				if (player != null)
				{
					result.add(player);
				}
			}
		}
		logger.info("returning " + result.size() + " players");
		
		return result;
	}

	@Override
	public boolean updatePlayerByNumber(int number, Player player)
	{
		logger.info("updating player " + number);

		Player playerOld = findById(number);
		boolean result = false;
		if (playerOld != null)
		{
			playerOld.setAge(player.getAge());
			playerOld.setNumber(player.getNumber());
			playerOld.setName(player.getName());
			result = true;
		}
		else
		{
			logger.warn("player " + number + " not found");
		}
		return result;

	}

	@Override
	public boolean deletePlayer(int number) 
	{
		logger.info("deleting player " + number);

		Player player = findById(number);
		boolean result = false;
		
		if (player != null)
		{
			team.remove(player);
			result = true;
		}
		else
		{
			logger.warn("player " + number + " not found");
		}
		return result;
	}

	@Override
	@WebMethod(exclude=true)
	public void foo() 
	{
		logger.info("Hello world!!!");
	}
	
	private Player findById(int number)
	{
		Player player = null;
		boolean found = false;
		ListIterator<Player> listIterator = team.listIterator();
		
		while (listIterator.hasNext() && !found)
		{
			player = listIterator.next();
			if (player.getNumber() == number)
			{
				found = true;
			}
		}
		if (!found)
		{
			player = null;
		}
		
		return player;
	}

}
