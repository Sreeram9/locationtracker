/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sreeram;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Counts words in UTF8 encoded, '\n' delimited text received from the network every second.
 *
 * Usage: JavaNetworkWordCount <hostname> <port>
 * <hostname> and <port> describe the TCP server that Spark Streaming would connect to receive data.
 *
 * To run this on your local machine, you need to first run a Netcat server
 *    `$ nc -lk 9999`
 * and then run the example
 *    `$ bin/run-example org.apache.spark.examples.streaming.JavaNetworkWordCount localhost 9999`
 */
public final class SparkFileListener {
	private static final Pattern SPACE = Pattern.compile(" ");

	private static final MongoClient mongoClient = new MongoClient();
	private static final MongoDatabase database = mongoClient.getDatabase("location_tracker");
	private static final MongoCollection<Document> collection = database.getCollection("device");

	public static void main(String[] args) throws Exception {
		System.setProperty("hadoop.home.dir", "F:\\Photo");
		SparkConf sparkConf = new SparkConf().setAppName("JavaNetworkWordCount");
		sparkConf.setMaster("local[1]");
		sparkConf.set("spark.driver.memory","259522560");

		JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(10));

		JavaDStream<String> lines = ssc.textFileStream("file:\\G:\\Sample");
		lines.print();
		lines.foreachRDD(new VoidFunction<JavaRDD<String>>() {
			@Override
			public void call(JavaRDD<String> arg0) throws Exception {
				List<String> lines = arg0.collect();
				for(String line:lines){
					String[] words=line.split(" "); 				
					if(words!=null) {
						Bson filter = new Document("_id",Integer.parseInt(words[0]));
						Bson updateOperationDocument = new Document("locations",
								new Document().append("latitude",words[1])
								.append("longitude",words[2]).append("timestamp",
										new Date(Long.parseLong(words[3]))));
						Bson newValue = new Document("$push", updateOperationDocument);
						collection.findOneAndUpdate(filter,newValue);
					}
				}
			}
		});
		ssc.start();
		ssc.awaitTermination();
	}
}