package com.thegame.server.common.functional;

import java.text.MessageFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author afarre
 * @param <TYPE1>
 * @param <TYPE2>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuple<TYPE1,TYPE2> {
	
	private TYPE1 object1;
	private TYPE2 object2;
	
	
	public <T> Tuple<T,TYPE2> mutateFirst(final T _newFirst){
		return new Tuple<>(_newFirst,object2);
	}
	public <T> Tuple<TYPE1,T> mutateSecond(final T _newSecond){
		return new Tuple<>(object1,_newSecond);
	}
	public <T1,T2> Tuple<T1,T2> mutate(final T1 _newFirst,final T2 _newSecond){
		return new Tuple<>(_newFirst,_newSecond);
	}
	
	public Object[] toArray(){
		return new Object[]{object1,object2};
	}
	
	public String toString(final String _messageFormatPattern){
		return MessageFormat.format(_messageFormatPattern, toArray());
	}
}
