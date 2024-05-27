package com.bezkoder.springjwt;


import com.bezkoder.springjwt.security.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationListener {

    @Autowired
    protected InitService initService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initService.init();
    }
}