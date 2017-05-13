package com.thegame.server.engine.messages;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.common.functional.Tuple;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 *
 * @author afarre
 */
public abstract class BaseMessageBeanTest {
	
	private final Path messagesPath;
	private final Genson genson;

	public BaseMessageBeanTest(final String _folder){
		this.messagesPath=Paths.get("./src/test/resources/messages",_folder);
		this.genson=new GensonBuilder()
						//.useRuntimeType(true)
						.create();				
	}
	
	public abstract Optional<IsMessageBean> getBean(final String _json);
	
	
	/**
	 * Test of json-serialize method, of class AreaMessageBean.
	 * @throws java.io.IOException
	 */
	@Test
	public void testJSONSerialize() throws IOException {
		System.out.println("json-serialize");
		Files.list(this.messagesPath)
			.filter(path -> Files.exists(path))
			.filter(path -> Files.isReadable(path))
			.map(path -> new Tuple<>(path,LambdaUtils.readAllLines(path,StandardCharsets.UTF_8)))
			.filter(optionalListTuple -> optionalListTuple.getObject2().isPresent())
			.map(optionalListTuple -> optionalListTuple.mutateSecond( 
															optionalListTuple.getObject2()
																.get()
																.stream()
																.collect(Collectors.joining(""))))
			.map(jsonTuple -> jsonTuple.mutateFirst(jsonTuple.getObject1().getFileName().toString()))
			.peek(jsonTupleAndName -> System.out.println(MessageFormat.format("Testing file {0}", jsonTupleAndName.getObject1())))
			.map(jsonTupleAndName -> jsonTupleAndName.mutateFirst(getBean(jsonTupleAndName.getObject1())
																		.orElseThrow(()-> new RuntimeException(MessageFormat.format("Bean {0} not retrieved",jsonTupleAndName.getObject1())))))
			.forEach(testCase -> {
				try {
					System.out.println(testCase.toString("File {0} against bean {1}"));
					final String expResult=testCase.getObject2();
					final IsMessageBean instance=testCase.getObject1();
					String result=this.genson.serialize(instance);
					JSONAssert.assertEquals(expResult,result,false);
					System.out.println(testCase.toString("Test passed"));
				} catch (JSONException e) {
					e.printStackTrace();
					throw new AssertionError("JSON parse has failed",e);
				}catch(Throwable e){
					e.printStackTrace();
					throw new AssertionError("Assertion failed",e);
				}
			});
	}

	/**
	 * Test of json-deserialize method, of class AreaMessageBean.
	 * @throws java.io.IOException
	 */
	@Test
	public void testJSONDeserialize() throws IOException {
		System.out.println("json-deserialize");
		Files.list(this.messagesPath)
			.filter(path -> Files.exists(path))
			.filter(path -> Files.isReadable(path))
			.map(path -> new Tuple<>(path,LambdaUtils.readAllLines(path,StandardCharsets.UTF_8)))
			.filter(optionalListTuple -> optionalListTuple.getObject2().isPresent())
			.map(optionalListTuple -> optionalListTuple.mutateSecond( 
															optionalListTuple.getObject2()
																.get()
																.stream()
																.collect(Collectors.joining(""))))
			.map(jsonTuple -> jsonTuple.mutateFirst(jsonTuple.getObject1().getFileName().toString()))
			.peek(jsonTupleAndName -> System.out.println(MessageFormat.format("Testing file {0}", jsonTupleAndName.getObject1())))
			.map(jsonTupleAndName -> jsonTupleAndName.mutateFirst(getBean(jsonTupleAndName.getObject1())
																		.orElseThrow(()-> new RuntimeException(MessageFormat.format("Bean {0} not retrieved",jsonTupleAndName.getObject1())))))
			.forEach(testCase -> {
				System.out.println(testCase.toString("File {0} against bean {1}"));
				final String instance=testCase.getObject2();
				final IsMessageBean expected=testCase.getObject1();
				IsMessageBean result=this.genson.deserialize(instance,expected.getClass());
				Assert.assertEquals(expected, result);
				System.out.println(testCase.toString("Test passed"));
			});
	}
}
