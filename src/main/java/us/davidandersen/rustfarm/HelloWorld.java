package us.davidandersen.rustfarm;

import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

public class HelloWorld
{

	public static void main(final String[] args)
	{
		final SeedCatalog c = new SeedCatalog();
		final SeedEvaluator se = new SeedEvaluator(c);
		final Factory<Genotype<IntegerGene>> gtf = Genotype.of(IntegerChromosome.of(0, c.maxIndex()), 9);

		final Engine<IntegerGene, Integer> engine = Engine.builder(g -> se.eval(g), gtf)
				.populationSize(1000)
				.alterers(new Mutator<>(0.05))
				.build();

		final Genotype<IntegerGene> result = engine.stream()
				// .limit(Limits.bySteadyFitness(100))
				.limit(10000)
				.collect(EvolutionResult.toBestGenotype());

		System.out.println("Hello World:\n" + result);

		// result.
		final Planter p = new Planter();
		for (int i = 0; i < result.length(); i++)
		{
			final int seedId = result.get(i).gene().intValue();
			if (seedId == 0)
			{
				continue;
			}
			final Seed seed = c.getSeed(seedId);
			p.addSeed(seed);
			System.out.println(seed.genes);
		}
		System.out.println("------");
		System.out.println(p.grow().genes);
	}
}
