package com.ocl.hadoop.wordcount.reducer

import java.io.IOException
import java.util.Iterator

import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.MapReduceBase
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reducer
import org.apache.hadoop.mapred.Reporter


class WordCountReducer extends MapReduceBase with Reducer[Text, IntWritable, Text, IntWritable] {
    @throws[IOException]
    def reduce(key: Text, values: Iterator[IntWritable], output: OutputCollector[Text, IntWritable], reporter: Reporter) {
      import scala.collection.JavaConversions._
      val sum = values.toList.reduce((valueOne, valueTwo) => new IntWritable(valueOne.get() + valueTwo.get()))
      output.collect(key,  new IntWritable(sum.get()))
    }
  }