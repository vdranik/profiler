package com.profiler.UIApp.bootstrap;


import com.profiler.UIApp.domain.UIData;
import com.profiler.UIApp.repositories.UIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class UIApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UIRepository uiRepository;

    @Autowired
    public void setUIRepository(UIRepository uiRepository) {
        this.uiRepository = uiRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        UIData uiData1 = new UIData("Some UI data1");
        UIData uiData2 = new UIData("Some UI data2");
        UIData uiData3 = new UIData("Some UI data3");
        UIData uiData4 = new UIData("Some UI data4");
        UIData uiData5 = new UIData("Some UI data5");
        uiRepository.save(uiData1);
        uiRepository.save(uiData2);
        uiRepository.save(uiData3);
        uiRepository.save(uiData4);
        uiRepository.save(uiData5);
    }
}
