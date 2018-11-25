package telran.m2m.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;

@EnableBinding(IDispatcher.class)
public class SensorDispatcher {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	IDispatcher dispatcher;
	@Value("${min}")
	int min;
	@Value("${max}")
	int max;
	
	@StreamListener(IDispatcher.INPUT)
	public void getSensorData(String sensorData) throws JsonParseException, JsonMappingException, IOException {
		Sensor sensor = mapper.readValue(sensorData, Sensor.class);
		if (sensor.getData() < min) {
			dispatcher.smallNumbers()
			.send(MessageBuilder.withPayload(sensorData).build());
			return;
		}
		
		if (sensor.getData() > max) {
			dispatcher.bigNumbers()
			.send(MessageBuilder.withPayload(sensorData).build());
			return;
		}
		System.out.println(sensor);
	}

}





