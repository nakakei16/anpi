package com.naka.anpi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResultChecker {

	public static Boolean isBuildSuccess(String jenkinsResult) {

		try {

			List<BuildResult> buildResults = json2buildResults(jenkinsResult);
			for (BuildResult buildResult : buildResults) {
				if(buildResult.result == null) continue; 
				if (buildResult.result.equals("FAILURE")) {
					return false;
				}
			}
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static List<BuildResult> json2buildResults(String jenkinsResult)
			throws IOException, JsonParseException, JsonMappingException {

		List<BuildResult> buildResults = new ArrayList<BuildResult>();
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(jenkinsResult);

		while (parser.nextToken() != JsonToken.END_OBJECT) {
			ObjectMapper mapper = new ObjectMapper();
			buildResults.add(mapper.readValue(jenkinsResult, BuildResult.class));
		}
		return buildResults;
	}
}
