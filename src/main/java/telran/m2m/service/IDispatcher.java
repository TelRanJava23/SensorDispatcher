package telran.m2m.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface IDispatcher extends Sink {
	String BIG_NUMBER = "bigNumbers";
	String SMALL_NUMBER = "smallNumbers";
	
	@Output(BIG_NUMBER)
	MessageChannel bigNumbers();
	
	@Output(SMALL_NUMBER)
	MessageChannel smallNumbers();

}
