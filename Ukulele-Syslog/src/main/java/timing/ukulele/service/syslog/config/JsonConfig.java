//package timing.ukulele.service.syslog.config;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.DateSerializer;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.springframework.boot.jackson.JsonComponent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@JsonComponent
//public class JsonConfig {
//
//    @Bean
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        //或略value为null时key的输出
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        //序列化成json时，将所有的Long变成string，以解决js中的精度丢失。
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleModule.addSerializer(Date.class, new DateSerializer(false,simpleDateFormat));
//        objectMapper.registerModule(simpleModule);
//        return objectMapper;
//    }
//}
