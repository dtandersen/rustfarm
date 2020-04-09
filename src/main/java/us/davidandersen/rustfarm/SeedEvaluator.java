package us.davidandersen.rustfarm;

import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.IntegerGene;
import io.jenetics.Phenotype;
import io.jenetics.engine.EvolutionResult;

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

		// if (seed.getGeneCount("g") > 4)
		// {
		// return 0;
		// }
		// if (seed.getGeneCount("y") > 4)
		// {
		// return 0;
		// }
		final int gymax = Math.max(seed.getGeneCount("g"), seed.getGeneCount("y"));
		final int gymin = Math.min(seed.getGeneCount("g"), seed.getGeneCount("y"));
		// final int fitness = ("g".contains(seed.getCol(3)) ? 10 : -10);
		// final int fitness = ("g".contains(seed.getCol(3)) ? 10 : -10) -
		// gt.length();
		final boolean hasG3 = "g".contains(seed.getCol(0));
		final boolean hasG5 = "y".contains(seed.getCol(5));
		int fitness = 0;
		// final int fitness = seed.getGeneCount("g") + seed.getGeneCount("y") +
		// hasG;
		if (!hasG3)
		{
			return -gt.length();
		}
		// // if (hasG3)
		// // {
		// // fitness += 100;
		// // }
		// if (!hasG5)
		// {
		// fitness += p.colCount("y", 5);
		// }
		// else
		// {
		fitness += 10 + Math.min(seed.getGeneCount("g"), 4) + Math.min(seed.getGeneCount("y"), 2);
		// }
		// if (hasG5)
		// {
		// fitness += 50;
		// }
		//
		// fitness += p.colCount("g", 3);
		// fitness += p.colCount("g", 5);
		// // if (hasG3 && hasG5)
		// // {
		// fitness += seed.getGeneCount("g") + seed.getGeneCount("y") -
		// seed.getGeneCount("w") - seed.getGeneCount("x") -
		// seed.getGeneCount("?");
		// }
		// else
		// {
		// // fitness -= gt.length();
		// }
		// System.out.println("" + seed.genes + ", " + fitness);
		return fitness;
		// return 100 * gymax + 300 * gymin;
		// return seed.getGeneCount("g") * 11 + seed.getGeneCount("y") * 10;
		// return seed.getGeneCount("g") * 15 + seed.getGeneCount("y") * 10 - 3
		// * seed.getGeneCount("w") - 2 * seed.getGeneCount("x") -
		// seed.getGeneCount("h") - seed.getGeneCount("?") - gt.length();
	}

	static Phenotype<IntegerGene, Integer> best = null;

	// Update the best phenotype, if available.
	static void update(final EvolutionResult<IntegerGene, Integer> result)
	{
		if (best == null || best.compareTo(result.bestPhenotype()) < 0)
		{
			best = result.bestPhenotype();
			System.out.println("Found new best phenotype: " + best);
		}
	}
}
