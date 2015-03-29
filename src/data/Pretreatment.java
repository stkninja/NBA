package data;

import data.savePOs.SaveMBasicPO;
import data.savePOs.SavePBasicPO;
import data.savePOs.SaveTBasicPO;

public class Pretreatment {

	public static void pretreatment(){
		redoTBasic();
		redoPBasic();
		redoMBasic();
	}

	public static void redoPBasic(){
		SavePBasicPO.savePBasicPO();
	}
	
	public static void redoTBasic(){
		SaveTBasicPO.saveTBasicInfo();
	}
	
	public static void redoMBasic(){
		SaveMBasicPO.saveAll();
	}
}
