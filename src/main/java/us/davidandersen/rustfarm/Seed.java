package us.davidandersen.rustfarm;

import java.util.ArrayList;
import java.util.List;

public class Seed
{
	int id;

	List<String> genes;

	public Seed(final String line)
	{
		genes = new ArrayList<String>();
		genes.add("" + line.charAt(0));
		genes.add("" + line.charAt(1));
		genes.add("" + line.charAt(2));
		genes.add("" + line.charAt(3));
		genes.add("" + line.charAt(4));
		genes.add("" + line.charAt(5));
	}

	public Seed(final List<String> genes)
	{
		this.genes = genes;
	}

	public String getCol(final int i)
	{
		return genes.get(i);
	}

	public int getGeneCount(final String gene)
	{
		return (int)genes.stream().filter(g -> g.equals(gene)).count();
	}

	@Override
	public String toString()
	{
		return "" + genes;
	}
}
