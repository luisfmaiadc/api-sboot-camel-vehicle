package com.portfolio.luisfmdc.api_sboot_camel_vehicle.route;


import com.portfolio.luisfmdc.api_sboot_camel_vehicle.processor.FindWorkshopSpecialityProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    @Value("${api.workshop.url}")
    private String workshopUrl;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").contextPath("/api/orch");

        rest()
                .get("/workshops")
                .produces("application/json")
                .to("direct:findWorkshopByFilter");

        rest()
                .get("/workshops/specialty/{specialtyId}")
                .produces("application/json")
                .to("direct:findWorkshopSpeciality");

        from("direct:findWorkshopByFilter")
                .log("Requisicao recebida: ${header.CamelHttpUri}")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .toD(workshopUrl + "?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .log("Resposta recebida da API Workshop: ${body}");

        from("direct:findWorkshopSpeciality")
                .log("Requisicao recebida: ${header.CamelHttpUri}")
                .process(new FindWorkshopSpecialityProcessor())
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD(workshopUrl.concat("${exchangeProperty.targetUrl}?bridgeEndpoint=true&throwExceptionOnFailure=false"))
                .log("Resposta recebida da API Workshop: ${body}");
    }
}
