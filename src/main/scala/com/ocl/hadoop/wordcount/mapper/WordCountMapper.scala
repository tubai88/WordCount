package com.ocl.hadoop.wordcount.mapper

import java.io.IOException

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.MapReduceBase
import org.apache.hadoop.mapred.Mapper
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reporter

class WordCountMapper extends MapReduceBase with Mapper[LongWritable, Text, Text, IntWritable] {
    private final val one = new IntWritable(1)
    private val word = new Text()

    @throws[IOException]
    def map(key: LongWritable, value: Text, output: OutputCollector[Text, IntWritable], reporter: Reporter) {
      val line: String = value.toString
      line.split(" ").foreach {token =>
        word.set(token)
        output.collect(word, one)
      }
    }
  }