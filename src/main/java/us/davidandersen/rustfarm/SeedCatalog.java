package us.davidandersen.rustfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeedCatalog
{
	private final HashMap<Integer, Seed> seeds;

	public SeedCatalog()
	{
		seeds = new HashMap<>();
		BufferedReader reader;
		int i = 1;
		try
		{
			reader = new BufferedReader(new FileReader("seeds.txt"));
			String line = reader.readLine();
			while (line != null)
			{
				seeds.put(i, new Seed(line));
				i++;
				line = reader.readLine();
			}
			reader.close();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	Map<Integer, Seed> getSeeds()
	{
		return seeds;
	}

	public int maxIndex()
	{
		return getSeeds().size();
	}

	public Seed getSeed(final int seedId)
	{
		return getSeeds().get(seedId);
	}
}
