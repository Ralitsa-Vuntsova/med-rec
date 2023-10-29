package bg.sofia.uni.fmi.piss.project.medrec;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class MedrecApplication {

	@Bean
	public static HttpClient getHttpClient() {
		return HttpClient.newBuilder().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(MedrecApplication.class, args);
	}

}
