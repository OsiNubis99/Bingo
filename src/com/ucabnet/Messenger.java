package com.ucabnet;

import com.fazecast.jSerialComm.*;

public class Messenger {
	private SerialPort readPort;
	private SerialPort writePort;
	private BingoGame game;
	private Boolean pivo = false;
	private Boolean end = false;

	public Messenger(String read, String write, String gameMode, String cantidad) {
		BingoGame game = new BingoGame(gameMode, cantidad);
		game.setVisible(true);
		SerialPort readPort = SerialPort.getCommPort(read);
		SerialPort writePort = SerialPort.getCommPort(write);
		readPort.setComPortParameters(9600, 8, 1, 0);
		writePort.setComPortParameters(9600, 8, 1, 0);
		readPort.openPort();
		writePort.openPort();

		String mensaje = "B1" + "00-1" + "O75";
		writePort.writeBytes(mensaje.getBytes(), mensaje.length());
		readPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}

			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
					return;
				byte[] newData = new byte[readPort.bytesAvailable()];
				readPort.readBytes(newData, newData.length);
				String mensaje = new String(newData);
				if (mensaje.startsWith("B1") && mensaje.endsWith("O75")) {
					int numero = Integer.valueOf(mensaje.substring(2, 4));
					int gano = Integer.valueOf(mensaje.substring(5, 6));
					System.out.println("Received " + mensaje + " " + numero + " " + gano);
					if (numero == 0) {
						if (gano == 1) {
							pivo = true;
							gano--;
							System.out.println("Pivo");
						}
						System.out.println("no pivo");
					}
					if (pivo) {
						numero = game.generateNumber();
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (gano >= 1) {
							end = true;
						}
					}
					if (!end) {
						gano = gano + game.addNumber(numero);
						System.out.println(gano);

						String num = Integer.toString(numero);
						if (num.length() == 1)
							num = "0" + num;
						String response = "B1" + num + "-" + Integer.toString(gano) + "O75";
						System.out.println(response);
						writePort.writeBytes(response.getBytes(), response.length());
					}
				}
			}
		});
	}
}