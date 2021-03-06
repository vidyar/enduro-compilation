package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;
import utils.FileWriter;

public class FileWriterTest {
    FileReader fileReader;
    Enduro enduro;
    ArrayList<String> lines;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
        enduro = Enduro.getInstance();
        lines = new ArrayList<String>();
        Collections.addAll(lines, new String[]{"1", "2", "3", "4", "5"});
    }

    @After
    public void tearDown() throws Exception {
        lines = null;
    }

    @Test
    public void testCanWriteEMptyFile() {
        String file = enduro.getResourcePath("utils/testOutputEmptyFile.csv");
        FileWriter.writeFile(file, new ArrayList<String>().iterator());
        try {
            fileReader.readFileByLine(file);
        } catch (FileNotFoundException e) {
            Assert.fail("Write failed: FileNotFoundException!");
        }
    }

    @Test
    public void testCanWriteFile() {
        String file = enduro.getResourcePath("utils/testOutputFile.csv");
        FileWriter.writeFile(file, lines.iterator());
        Iterator<String> resultLines = null;
        try {
            resultLines = fileReader.readFileByLine(file);
        } catch (FileNotFoundException e) {
            Assert.fail("Write failed: FileNotFoundException!");
        }
        Assert.assertEquals("Should be 5", 5, lines.size());
        int index = 0;
        while (resultLines.hasNext()) {
            Assert.assertEquals("Should be equal!", lines.get(index++), resultLines.next());
        }
    }


}
