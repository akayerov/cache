package com.akayerov.cache;

import com.akayerov.cache.Cache;
import com.akayerov.cache.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class CasheApplicationTests {

    @Test
    void test_OneElement() {
        Cache cache =  new Cache(3, "LFU");

        String inString = "String 123";
        boolean result = cache.put("key1", inString);

        Assert.assertTrue ("Put string to cashe", result);
        Object outString = cache.get("key1");


        System.out.println(outString);
        Assert.assertEquals(inString, outString);
    }

    @Test
    void test_OneElement2() {
        Cache cache =  new Cache(3, "LFU");

        User user = new User(1,"andrey");

        boolean result = cache.put("key1", user);
        Assert.assertTrue ("Put string to cashe", result);

        Object outObject = cache.get("key1");
        System.out.println(outObject);
        Assert.assertEquals(user, outObject);
    }



    @Test
    void test2_LRU() {
        Cache cache =  new Cache(3, "LRU");

        String inString = "String 1";
        cache.put("key1", inString);
        inString = "String 2";
        cache.put("key2", inString);
        inString = "String 3";
        cache.put("key3", inString);
        inString = "String 4";
        cache.put("key4", inString);

        cache.prt();

        Assert.assertEquals(null,cache.get("key1"));
        Assert.assertEquals("String 2",cache.get("key2"));
        Assert.assertEquals("String 3",cache.get("key3"));
        Assert.assertEquals("String 4",cache.get("key4"));
    }

    @Test
    void test3_LFU() {
        Cache cache =  new Cache(3, "LFU");

        String inString = "String 1";
        cache.put("key1", inString);
        cache.get("key1");
        cache.get("key1");

        inString = "String 2";
        cache.put("key2", inString);
        cache.get("key2");
        cache.get("key2");
        cache.get("key2");

        inString = "String 3";
        cache.put("key3", inString);

        inString = "String 4";
        cache.put("key4", inString);

        cache.prt();

        Assert.assertEquals("String 1",cache.get("key1"));
        Assert.assertEquals("String 2",cache.get("key2"));
        Assert.assertEquals(null,cache.get("key3"));
        Assert.assertEquals("String 4",cache.get("key4"));
    }

}
