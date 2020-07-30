package com.clientname.app.exe;

import java.io.IOException;

import com.clientname.app.tests.RPA_MYTE;

public class EXE_RPA_MYTE {

	public static void main(String[] args) throws IOException {
		RPA_MYTE RPA_myte= new RPA_MYTE();
		RPA_myte.beforeTest();
		RPA_myte.MYTE_RPA();
		RPA_myte.afterTest();
	}

}
