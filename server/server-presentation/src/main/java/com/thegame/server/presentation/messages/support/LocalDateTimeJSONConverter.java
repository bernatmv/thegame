package com.thegame.server.presentation.messages.support;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author afarre
 */
public class LocalDateTimeJSONConverter implements Converter<LocalDateTime> {

	@Override
	public void serialize(final LocalDateTime _t,final ObjectWriter _writer,final Context _cntxt) throws Exception {
		_writer.writeNumber(_t.toEpochSecond(ZoneOffset.UTC));
	}
	@Override
	public LocalDateTime deserialize(final ObjectReader _reader,final Context _cntxt) throws Exception {
		return LocalDateTime.ofEpochSecond(_reader.valueAsLong(), 0, ZoneOffset.UTC);
	}
}
