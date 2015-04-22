package ui;

import data.Pretreatment;

public class UpdateThread implements Runnable {
	public void run() {
		Pretreatment.pretreatment();
	}
}
