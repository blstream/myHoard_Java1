package com.blstream.myhoard.biz.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class RestDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {

		// http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/validation.html#format-configuring-formatting-mvc

		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);

	}

}
