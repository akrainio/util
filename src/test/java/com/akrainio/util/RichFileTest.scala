package com.akrainio.util

import java.io.File
import java.nio.file.Files.createTempDirectory

import com.akrainio.util.RichFileTest._
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FileUtils.writeByteArrayToFile
import org.apache.commons.io.IOUtils.readFully
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class RichFileTest {

  @Test
  def testSuccessfulRead() = doTest { dir =>
    val file = new File(dir, "testSomething")
    var expected = Array[Byte](1, 2, 3)
    writeByteArrayToFile(file, expected)
    val result = file.read { in =>
      readFully(in, 3)
    }
    assertArrayEquals(expected, result)
  }

}

object RichFileTest {
  def doTest(func: File => Unit): Unit = {
    val dir = createTempDirectory(getClass.getName)
    try func(dir.toFile)
    finally FileUtils.deleteQuietly(dir.toFile)
  }
}
