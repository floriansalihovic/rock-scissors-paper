package io.fsa.rsp;

import org.junit.Assert;
import org.junit.Test;

public class StatsTest {

    @Test
    public void getDraws() {
        Assert.assertEquals(RSP.Result.DRAW, stats(0, 0).getResult());
        Assert.assertEquals(RSP.Result.DRAW, stats(1, 1).getResult());
        Assert.assertEquals(RSP.Result.DRAW, stats(2, 2).getResult());
    }

    @Test
    public void getPlayer1Wins() {
        Assert.assertEquals(RSP.Result.PLAYER_1, stats(0, 1).getResult());
        Assert.assertEquals(RSP.Result.PLAYER_1, stats(1, 2).getResult());
        Assert.assertEquals(RSP.Result.PLAYER_1, stats(2, 0).getResult());
    }

    @Test
    public void getPlayer2Wins() {
        Assert.assertEquals(RSP.Result.PLAYER_2, stats(1, 0).getResult());
        Assert.assertEquals(RSP.Result.PLAYER_2, stats(2, 1).getResult());
        Assert.assertEquals(RSP.Result.PLAYER_2, stats(0, 2).getResult());
    }

    private static RSP.Stats stats(final int value1, final int value2) {
        return new RSP.Stats(() -> RSP.Value.valueOf(value1), () -> RSP.Value.valueOf(value2));
    }
}
