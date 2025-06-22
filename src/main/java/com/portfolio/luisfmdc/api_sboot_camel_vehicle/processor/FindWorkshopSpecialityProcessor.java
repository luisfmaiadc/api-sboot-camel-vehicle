package com.portfolio.luisfmdc.api_sboot_camel_vehicle.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FindWorkshopSpecialityProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String specialtyId = exchange.getIn().getHeader("specialtyId", String.class);
        String url = "/specialty/" + specialtyId;
        exchange.setProperty("targetUrl", url);
    }
}
