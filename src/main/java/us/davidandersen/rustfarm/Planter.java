package us.davidandersen.rustfarm;

import java.util.ArrayList;
import java.util.List;

public class Planter
{
	List<Seed> seeds = new ArrayList<Seed>();

	public void addSeed(final Seed seed)
	{
		seeds.add(seed);
	}

	public int seedCount()
	{
		return seeds.size();
	}

	public Seed grow()
	{
		final List<String> genes = new ArrayList<String>();
		for (int i = 0; i < 6; i++)
		{
			final GeneCount counts = colCounts(i);
			if (counts.tie())
			{
				genes.add("?");
			}
			else
			{
				final String highest = counts.highest();

				genes.add(highest);
			}
		}

		return new Seed(genes);
	}

	public int size()
	{
		return seeds.size();
	}

	private GeneCount colCounts(final int i)
	{
		final GeneCount gc = new GeneCount();
		for (final Seed seed : seeds)
		{
			gc.add(seed.getCol(i));
		}
		return gc;
	}

	public int colCount(final String gene, final int i)
	{
		final GeneCount cc = colCounts(i);
		return cc.countOf(gene);
	}
}
