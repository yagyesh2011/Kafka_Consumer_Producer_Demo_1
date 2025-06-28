package com.yagyesh.movie.movieconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConfig {

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setRecordFilterStrategy(record -> record.headers().lastHeader("__TypeId__") != null); // Example filter condition
//        return factory;
//    }
}
