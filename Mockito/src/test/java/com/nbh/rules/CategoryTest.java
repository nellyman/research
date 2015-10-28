/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author  nhardwic
 */
@RunWith(Categories.class)
@IncludeCategory(CrazyTests.class)
@SuiteClasses( { SomeTest.class, OtherTest.class }) // Note that Categories is a kind of Suite
public class CategoryTest {
    // Will run SomeTest.b and OtherTest.c, but not SomeTest.a
}
@RunWith(Categories.class)
@IncludeCategory(CrazyTests.class)
@ExcludeCategory(SmartTests.class)
@SuiteClasses( { SomeTest.class, OtherTest.class })
class CrazyTestSuite {
    // Will run SomeTest.b, but not SomeTest.a or OtherTest.c
}

interface SmartTests { /* category marker */ }
interface CrazyTests { /* category marker */ }

class SomeTest {
    @Test
    public void a() {
        Assert.fail();
    }

    @Category(CrazyTests.class)
    @Test
    public void b() {
    }
}
@Category({CrazyTests.class, SmartTests.class})
class OtherTest {
    @Test
    public void c() {
    }
}


