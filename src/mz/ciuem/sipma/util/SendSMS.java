package mz.ciuem.sipma.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SendSMS {
	
	public  static String sms(String to, String text) throws UnirestException {
		HttpResponse<String> response; response = Unirest.post("https://api.infobip.com/sms/1/text/single")

				.header("authorization", " Basic dWVtMjAxMzp1ZW1hZG1pc3Nhbw==")
				.header("content-type", "application/json").header("accept", "application/json")
				.body("{\"from\":\"UEM\",\"to\":\"+" + to + "\",\"text\":\"".concat(text) + "\"}").asString();
	return response.getBody();
}
	
	public static void main(String[] args) throws UnirestException {
		SendSMS.sms("258840665903", "Sucesso");
	}

}