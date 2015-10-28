/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author  nhardwic
 */

public class TemporaryFolderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        final File createdFile = this.folder.newFile("myfile.txt");
        final File createdFolder = this.folder.newFolder("mysubfolder");
    }
}
