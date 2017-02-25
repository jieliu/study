package com.tianma.spring.mvc.config;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.ToolboxConfiguration;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;


/**
 * Created by fs_dev on 16-2-20.
 * Author: liu jie
 * Mail: fiboliu@163.com
 * Company: egoo net
 */

@Configuration
@EnableAutoConfiguration
public class VelocityConfig {

    @Bean(name = "velocityViewResolver")
    public VelocityViewResolver velocityViewResolver(VelocityProperties properties) {

        VelocityViewResolver resolver = new VelocityViewResolver();
        properties.applyToViewResolver(resolver);
        resolver.setViewClass(VelocityLayoutToolboxView.class);//
        return resolver;
    }
}


class VelocityLayoutToolboxView extends VelocityLayoutView {

    private VelocityLayoutToolboxView() {
        //layout set
        super.setLayoutKey("layout");
        super.setLayoutUrl("layout/default.vm");
    }

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ViewToolContext ctx = new ViewToolContext(this.getVelocityEngine(), request, response, this.getServletContext());
        //load velocity toolbox
        if (this.getToolboxConfigLocation() != null) {
            XmlFactoryConfiguration factory = new XmlFactoryConfiguration();
            factory.read(ResourceUtils.getURL(this.getToolboxConfigLocation()).openStream());
            ToolboxFactory toolboxFactory = factory.createFactory();
            toolboxFactory.configure(factory);
            Collection<ToolboxConfiguration> toolboxes = factory.getToolboxes();
            for (ToolboxConfiguration tc : toolboxes) {

                ctx.addToolbox(toolboxFactory.createToolbox(tc.getScope()));// 这样操作后就可以用工具里面的东西了。
            }
        }

        if (model != null && !model.isEmpty()) {
            ctx.putAll(model);
        }
        return ctx;
    }
}