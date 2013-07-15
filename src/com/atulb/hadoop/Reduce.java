package com.atulb.hadoop;

import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class Reduce extends Reducer<Text, Text, Text, Text> {

	private Text docIds = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		HashSet<Text> uniqueDocIds = new HashSet<Text>();
		for (Text docId : values) {
			uniqueDocIds.add(new Text(docId));
		}
		docIds.set(new Text(StringUtils.join(uniqueDocIds, ",")));
		context.write(key, docIds);
	}
}
