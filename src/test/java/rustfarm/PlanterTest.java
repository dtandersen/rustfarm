package rustfarm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import us.davidandersen.rustfarm.Planter;
import us.davidandersen.rustfarm.Seed;

public class PlanterTest
{
	@Test
	public void t1()
	{
		final Planter p = new Planter();
		p.addSeed(new Seed("gyhwxx"));
		final Seed result = p.grow();
		assertThat(result.getCol(0), equalTo("g"));
		assertThat(result.getCol(1), equalTo("y"));
		assertThat(result.getCol(2), equalTo("h"));
		assertThat(result.getCol(3), equalTo("w"));
		assertThat(result.getCol(4), equalTo("x"));
		assertThat(result.getCol(5), equalTo("x"));

		assertThat(result.getGeneCount("g"), equalTo(1));
		assertThat(result.getGeneCount("x"), equalTo(2));
	}

	@Test
	public void t2()
	{
		final Planter p = new Planter();
		p.addSeed(new Seed("gyhwxx"));
		p.addSeed(new Seed("ggggyy"));
		final Seed result = p.grow();
		assertThat(result.getCol(0), equalTo("g"));
		assertThat(result.getCol(1), equalTo("?"));
		assertThat(result.getCol(2), equalTo("h"));
		assertThat(result.getCol(3), equalTo("w"));
		assertThat(result.getCol(4), equalTo("x"));
		assertThat(result.getCol(5), equalTo("x"));

		assertThat(result.getGeneCount("g"), equalTo(1));
		assertThat(result.getGeneCount("x"), equalTo(2));
	}
}
