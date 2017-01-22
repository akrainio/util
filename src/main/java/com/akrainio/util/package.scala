package com.akrainio

import java.io.{File, FileInputStream}

package object util {
  implicit class RichFile(val file : File) extends AnyVal {

    def read[T](f: FileInputStream => T): T = {
      val inputStream = new FileInputStream(file)
      try {
        f(inputStream)
      } finally {
        inputStream.close()
      }
    }

  }
}
