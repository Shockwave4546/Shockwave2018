package org.usfirst.frc4546.Shockwave2018;

public class AutoPosition {
	private int n;

	public AutoPosition(int v) {
		n = v;
	}

	/** Gets the position of the robot from the Chooser */
	public String getString() {
		String send = "";

		if (n == 1) {
			send = "Left";
		} else if (n == 2) {
			send = "Center";
		} else if (n == 3) {
			send = "Right";
		} else if (n == 4) {
			send = "Disable";
		} else {
			send = "not set";
		}

		return send;
	}

	public void Auto() {

	}
}