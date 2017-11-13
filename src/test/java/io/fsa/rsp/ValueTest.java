package io.fsa.rsp;

import org.junit.*;

public class ValueTest {

    @Test(expected = IllegalArgumentException.class)
    public void valueOf() {
        Assert.assertEquals(RSP.Value.ROCK, RSP.Value.valueOf(0));
        Assert.assertEquals(RSP.Value.SCISSORS, RSP.Value.valueOf(1));
        Assert.assertEquals(RSP.Value.PAPER, RSP.Value.valueOf(2));
        RSP.Value.valueOf(3);
    }
}
