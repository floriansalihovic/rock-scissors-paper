package io.fsa.rsp;

import org.junit.Assert;
import org.junit.Test;

public class ResultTest {

    @Test
    public void getDraws() {
        Assert.assertEquals(RSP.Result.DRAW, result(0, 0));
        Assert.assertEquals(RSP.Result.DRAW, result(1, 1));
        Assert.assertEquals(RSP.Result.DRAW, result(2, 2));
    }

    @Test
    public void getPlayer1Wins() {
        Assert.assertEquals(RSP.Result.PLAYER_1, result(0, 1));
        Assert.assertEquals(RSP.Result.PLAYER_1, result(1, 2));
        Assert.assertEquals(RSP.Result.PLAYER_1, result(2, 0));
    }

    @Test
    public void getPlayer2Wins() {
        Assert.assertEquals(RSP.Result.PLAYER_2, result(1, 0));
        Assert.assertEquals(RSP.Result.PLAYER_2, result(2, 1));
        Assert.assertEquals(RSP.Result.PLAYER_2, result(0, 2));
    }

    private static RSP.Result result(final int value1, final int value2) {
        return RSP.Result.getResult(() -> RSP.Value.valueOf(value1), () -> RSP.Value.valueOf(value2));
    }
}
