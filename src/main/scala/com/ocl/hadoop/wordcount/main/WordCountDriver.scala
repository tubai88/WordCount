package com.ocl.hadoop.wordcount.main

import java.io.IOException
import java.util._
import org.apache.hadoop.fs.Path
import org.apache.hadoop.conf._
import org.apache.hadoop.io._
import org.apache.hadoop.mapred._
import org.apache.hadoop.util._
import com.ocl.hadoop.wordcount.mapper.WordCountMapper
import com.ocl.hadoop.wordcount.reducer.WordCountReducer

object WordCountDriver {
  def main(args : Array[String]){
    val conf: JobConf = new JobConf(WordCountDriver.getClass)
    conf.setJobName("WordCountDriver")
    conf.setOutputKeyClass(classOf[Text])
    conf.setOutputValueClass(classOf[IntWritable])
    conf.setMapperClass(classOf[WordCountMapper])
    conf.setCombinerClass(classOf[WordCountReducer])
    conf.setReducerClass(classOf[WordCountReducer])
    conf.setInputFormat(classOf[TextInputFormat])
    conf.setOutputFormat(classOf[TextOutputFormat[Text, IntWritable]])
    FileInputFormat.setInputPaths(conf, new Path(args(0)))
    FileOutputFormat.setOutputPath(conf, new Path(args(1)))
    JobClient.runJob(conf)
  }
}