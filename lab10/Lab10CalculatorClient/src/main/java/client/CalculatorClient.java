package client;

import generated.*;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CalculatorClient extends WebServiceGatewaySupport {

	public int getSum(AddRequest addRequest) {

		AddResponse response = (AddResponse)
				getWebServiceTemplate().marshalSendAndReceive(addRequest);
		return response.getResult();
	}

	public int getSub(SubtractRequest subtractRequest) {

		SubtractResponse response = (SubtractResponse)
				getWebServiceTemplate().marshalSendAndReceive(subtractRequest);
		return response.getResult();
	}

	public int getMultiply(MultiplyRequest multiplyRequest) {

		MultiplyResponse response = (MultiplyResponse)
				getWebServiceTemplate().marshalSendAndReceive(multiplyRequest);
		return response.getResult();
	}
}


