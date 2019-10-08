package timing.ukulele.gateway.kaptcha.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * •Kaptcha验证码配置属性类
 * •@className: KaptchaProperties
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
@ConfigurationProperties(prefix = "ukulele.kaptcha")
@Data
public class KaptchaProperties {

    /**
     * 宽度
     */
    private Integer width = 200;
    /**
     * 高度
     */
    private Integer height = 50;
    /**
     * 内容
     */
    @NestedConfigurationProperty
    private Content content = new Content();
    /**
     * 背景色
     */
    @NestedConfigurationProperty
    private BackgroundColor backgroundColor = new BackgroundColor();
    /**
     * 字体
     */
    @NestedConfigurationProperty
    private Font font = new Font();
    /**
     * 边框
     */
    @NestedConfigurationProperty
    private Border border = new Border();

    @Data
    static class BackgroundColor {

        /**
         * 开始渐变色
         */
        private String from = "lightGray";
        /**
         * 结束渐变色
         */
        private String to = "white";

    }

    @Data
    static class Content {

        /**
         * 内容源
         */
        private String source = "abcdefghjklmnopqrstuvwxyz23456789";
        /**
         * 内容长度
         */
        private Integer length = 4;
        /**
         * 内容间隔
         */
        private Integer space = 2;

    }

    @Data
    static class Border {

        /**
         * 是否开启
         */
        private Boolean enabled = true;
        /**
         * 颜色
         */
        private String color = "black";
        /**
         * 厚度
         */
        private Integer thickness = 1;

    }

    @Data
    static class Font {

        /**
         * 名称
         */
        private String name = "Arial";
        /**
         * 颜色
         */
        private String color = "black";
        /**
         * 大小
         */
        private Integer size = 40;

    }

}
