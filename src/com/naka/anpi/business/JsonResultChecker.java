package com.naka.anpi.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naka.anpi.IConst;

public class JsonResultChecker {

	public static Boolean isBuildSuccess(String jenkinsResult) {
		try {
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createParser(jenkinsResult);

			while (parser.nextToken() != JsonToken.END_OBJECT) {

				if (parser.getCurrentName() == null)
					continue;

				if (parser.getValueAsString() == null)
					continue;

				if (parser.getCurrentName().equals(IConst.JSON_TARGET_NAME)
						&& parser.getValueAsString().equals(IConst.JSON_TARGET_VALUE)) {
					return false;
				}
			}
			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;

	}
}
