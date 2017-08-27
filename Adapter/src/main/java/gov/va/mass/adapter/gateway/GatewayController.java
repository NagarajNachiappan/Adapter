package gov.va.mass.adapter.gateway;

/**
 * Created by n_nac on 8/23/2017.
 */


import java.io.IOException;

import org.springframework.web.bind.annotation.*;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.AcknowledgmentCode;





@RequestMapping("/Adapter/Gateway/**")

@RestController

public class GatewayController {

    Message HL7Message;

    @RequestMapping(method = RequestMethod.POST, produces = "application/hl7-v2+er7")
    public String HL7Handler(@RequestHeader(value = "Content-Type") String contentType,@RequestHeader(value = "Content-Length") int contentLength, @RequestBody String requestBody) {
        HapiContext context = new DefaultHapiContext();
        Parser genericParser = context.getGenericParser();
        Message hapiMessage, ackResponse;
        String ACK;
        hapiMessage = null;
        ACK= null;
        try {
            System.out.println(" ======================================================");
            System.out.println("Happy Message Content type is   :" + contentType);
            System.out.println(" ======================================================");
            System.out.println("Happy Message Content Length is   :" + contentLength);
            System.out.println(" ======================================================");
            System.out.println("Happy Message is   :" + requestBody);
            hapiMessage = genericParser.parse(requestBody);
            ackResponse = hapiMessage.generateACK();
            ACK = ackResponse.toString().replace("\r", "\r\n");;
            System.out.println(" ======================================================");
            System.out.println("Happy ACK Response is \n" + ACK);
            System.out.println("Message Processed");
            System.out.println(" ======================================================");
        } catch (HL7Exception e) {
            e.printStackTrace();
            try {
                ackResponse = hapiMessage.generateACK(AcknowledgmentCode.AE, new HL7Exception("HL7Exception"));
                ACK = ackResponse.toString();
            } catch (Exception ioe) {
                ioe.printStackTrace();
                return ACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                ackResponse = hapiMessage.generateACK(AcknowledgmentCode.AE, new HL7Exception("IOException"));
                ACK = ackResponse.toString();
            } catch (Exception ioe) {
                ioe.printStackTrace();
                return ACK;
            }
        }
        return ACK;
    }
}