package com.util;


import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message.MessageEncodings;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SendMessage {
	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(AGateway agateway, OutboundMessage outboundmessage) {
			//System.out.println("Outbound handler called from Gateway: " + agateway);
			//System.out.println(outboundmessage);
			
		}

		public void process(String arg0, OutboundMessage arg1) {
			
			
		}
	}


	public void sendSMS(String mobilePhones, String content) throws GatewayException {
		Service srv = new Service();
		OutboundMessage msg;
		OutboundNotification outboundNotification = new OutboundNotification();
//		srv = new Service();
	
		SerialModemGateway gateway = new SerialModemGateway("modem.com4", "COM4", 9600, "", ""); // 设置端口与波特率
		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
//		gateway.setOutboundNotification(outboundNotification);
		srv.setOutboundMessageNotification(outboundNotification);
		srv.addGateway(gateway);
		//System.out.println("初始化成功，准备开启服务");
		try {
			srv.startService();
			//System.out.println("服务启动成功");
			String[] phones = mobilePhones.split(",");
			for (int i = 0; i < phones.length; i++) {
				msg = new OutboundMessage(phones[i], content);
				msg.setEncoding(MessageEncodings.ENCUCS2); // 中文
				srv.sendMessage(msg);
			}
			srv.stopService();
                        srv.removeGateway(gateway);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws GatewayException {
		SendMessage sendMessage = new SendMessage();
		String [] sms={"15901431929"};
		for (int i = 0; i < sms.length; i++) {
			sendMessage.sendSMS(sms[i], "测试短信");
		}
		
	}
}