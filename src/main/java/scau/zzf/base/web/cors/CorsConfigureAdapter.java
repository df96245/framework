package scau.zzf.base.web.cors;

import org.apache.http.config.Registry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zzf on 2017/1/19.
 */
public class CorsConfigureAdapter  extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/user").allowedOrigins("http://localhost:3000");
            registry.addMapping("/login").allowedOrigins("http://localhost:3000");
            registry.addMapping("/logout").allowedOrigins("http://localhost:3000");
            registry.addMapping("/order").allowedOrigins("http://localhost:3000");

    }
}
