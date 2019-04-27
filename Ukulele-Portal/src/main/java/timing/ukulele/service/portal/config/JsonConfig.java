//package timing.ukulele.service.portal.config;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.springframework.boot.jackson.JsonComponent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//@JsonComponent
//public class JsonConfig {
////    private final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
////
////    @Autowired
////    public JsonConfig(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
////        this.jackson2ObjectMapperBuilder = jackson2ObjectMapperBuilder;
////    }
////
////    @Bean
////    public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {
////        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
////        // ObjectMapper为了保障线程安全性，里面的配置类都是一个不可变的对象
////        // 所以这里的setDateFormat的内部原理其实是创建了一个新的配置类
////        DateFormat dateFormat = mapper.getDateFormat();
////        mapper.setDateFormat(new MyDateFormat(dateFormat));
////        return new MappingJackson2HttpMessageConverter(mapper);
////    }
//    @Bean
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        //或略value为null时key的输出
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        //序列化成json时，将所有的Long变成string，以解决js中的精度丢失。
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        return objectMapper;
//    }
////    protected void configureMessageConverters(List<HttpMessageConverter> converters) {
////        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
////        ObjectMapper objectMapper = new ObjectMapper();
////        SimpleModule simpleModule = new SimpleModule();
////        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
////        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
////        objectMapper.registerModule(simpleModule);
////        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
////        converters.add(jackson2HttpMessageConverter);
////    }
//}
