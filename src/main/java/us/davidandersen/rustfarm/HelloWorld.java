package us.davidandersen.rustfarm;

import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.Phenotype;
import io.jenetics.SinglePointCrossover;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.EvolutionStatistics;
import io.jenetics.engine.Limits;
import io.jenetics.util.Factory;

public class HelloWorld
{

	public static void main(final String[] args)
	{
		final SeedCatalog c = new SeedCatalog();
		final SeedEvaluator se = new SeedEvaluator(c);
		final Factory<Genotype<IntegerGene>> gtf = Genotype.of(IntegerChromosome.of(0, c.maxIndex()), 9);
		for (final Seed seed : c.getSeeds().values())
		{
			System.out.println(seed);
		}

		final Engine<IntegerGene, Integer> engine = Engine.builder(g -> se.eval(g), gtf)
				.populationSize(100)
				.alterers(new Mutator<>(.2), new SinglePointCrossover<>(.1))
				// .offspringFraction(0.7)
				// .survivorsSelector(new RouletteWheelSelector<>())
				// .offspringSelector(new TournamentSelector<>())
				.optimize(Optimize.MAXIMUM)
				.build();

		final EvolutionStatistics<Integer, ?> statistics = EvolutionStatistics.ofNumber();

		final Phenotype<IntegerGene, Integer> best = engine.stream()
				.limit(Limits.bySteadyFitness(5000))
				// .limit(100000)
				.peek(statistics)
				.peek(x -> se.update(x))
				.collect(EvolutionResult.toBestPhenotype());

		System.out.println("Hello World:\n" + best);
		final Genotype<IntegerGene> result = best.genotype();

		// result.
		final Planter p = new Planter();
		// result.
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
		System.out.println(best.fitness());
		System.out.println(p.grow().genes);
	}
}
