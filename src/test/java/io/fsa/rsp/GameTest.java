package io.fsa.rsp;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void getStoneValue() throws Exception {
        Assert.assertEquals(RSP.Value.ROCK.ordinal(), new RSP.Game().getRockValue());
    }

    @Test
    public void getNumberOfValues() throws Exception {
        Assert.assertEquals(RSP.Value.values().length, new RSP.Game().getNumberOfValues());
    }

    @Test
    public void getResults() throws Exception {
        final RSP.Game game = new RSP.Game();
        Assert.assertEquals(0, game.getStats().size());
        Assert.assertEquals(RSP.Result.DRAW, game.run(0, 0));
        Assert.assertEquals(1, game.getStats().size());
    }
}
