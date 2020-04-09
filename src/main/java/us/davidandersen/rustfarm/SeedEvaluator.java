package us.davidandersen.rustfarm;

import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.IntegerGene;

public class SeedEvaluator
{
	private final SeedCatalog c;

	public SeedEvaluator(final SeedCatalog c)
	{
		this.c = c;
	}

	public int eval(final Genotype<IntegerGene> gt)
	{
		final Planter p = new Planter();

		for (int i = 0; i < gt.length(); i++)
		{
			final Chromosome<IntegerGene> x = gt.get(i);
			final int seedId = x.gene().intValue();
			if (seedId == 0)
			{
				continue;
			}
			final Seed seed = c.getSeed(seedId);
			p.addSeed(seed);
		}

		if (p.size() == 0)
		{
			return 0;
		}

		final Seed seed = p.grow();

		if (seed.getGeneCount("g") > 4)
		{
			return 0;
		}
		if (seed.getGeneCount("y") > 4)
		{
			return 0;
		}
		final int gymax = Math.max(seed.getGeneCount("g"), seed.getGeneCount("y"));
		final int gymin = Math.min(seed.getGeneCount("g"), seed.getGeneCount("y"));
		// return 10 * gymax + gymin * 10 - gt.length();
		return seed.getGeneCount("g") * 11 + seed.getGeneCount("y") * 10;
		// return seed.getGeneCount("g") * 15 + seed.getGeneCount("y") * 10 - 3
		// * seed.getGeneCount("w") - 2 * seed.getGeneCount("x") -
		// seed.getGeneCount("h") - seed.getGeneCount("?") - gt.length();
	}
}
