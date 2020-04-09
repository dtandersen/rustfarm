package us.davidandersen.rustfarm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneCount
{
	private final Map<String, Integer> genes = new HashMap<>();

	public void add(final String gene)
	{
		final Integer x = genes.get(gene);
		if (x == null)
		{
			genes.put(gene, 1);
		}
		else
		{
			genes.put(gene, x + 1);
		}
	}

	public String highest()
	{
		final List<GeneNode> nodes = new ArrayList<>();
		for (final String gene : genes.keySet())
		{
			final Integer v = genes.get(gene);
			final GeneNode gn = new GeneNode(gene, v);
			nodes.add(gn);
		}

		nodes.sort(new ComparatorImplementation());
		// System.out.println(nodes);
		return nodes.get(0).gene;
	}

	public boolean tie()
	{
		final List<GeneNode> nodes = new ArrayList<>();
		for (final String gene : genes.keySet())
		{
			final Integer v = genes.get(gene);
			final GeneNode gn = new GeneNode(gene, v);
			nodes.add(gn);
		}

		nodes.sort(new ComparatorImplementation());

		if (nodes.size() >= 2 && (nodes.get(0).getWeight() == nodes.get(1).getWeight()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int countOf(final String gene)
	{
		final Integer integer = genes.get(gene);
		if (integer == null)
		{
			return 0;
		}

		return integer;
	}

	private final class ComparatorImplementation implements Comparator<GeneNode>
	{
		@Override
		public int compare(final GeneNode o1, final GeneNode o2)
		{
			return o2.getWeight() - o1.getWeight();
		}
	}

	static class GeneNode
	{
		private final String gene;

		private final Integer count;

		public GeneNode(final String gene, final Integer count)
		{
			this.gene = gene;
			this.count = count;
		}

		public int getWeight()
		{
			return count * weightOf(gene);
		}

		private int weightOf(final String gene)
		{
			if ("wxh".contains(gene))
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}

		@Override
		public String toString()
		{
			return "GeneCount[" +
					"gene=" + gene +
					", count=" + count +
					", weight=" + getWeight() +
					"]";
		}
	}
}
