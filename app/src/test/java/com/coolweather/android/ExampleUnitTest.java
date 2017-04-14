package com.coolweather.android;

import com.coolweather.android.utils.TimeUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetUntilTomorrowTime() throws Exception{
        long t = TimeUtils.getUntilTomorrowTime();
        assertEquals(121321, t);
    }
}