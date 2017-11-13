package io.fsa.rsp;

import org.junit.*;

public class ValueTest {

    @Test(expected = IllegalArgumentException.class)
    public void valueOf() throws Exception {
        Assert.assertEquals(RSP.Value.valueOf(0), RSP.Value.ROCK);
        Assert.assertEquals(RSP.Value.valueOf(1), RSP.Value.SCISSORS);
        Assert.assertEquals(RSP.Value.valueOf(2), RSP.Value.PAPER);
        RSP.Value.valueOf(3);
    }
}
