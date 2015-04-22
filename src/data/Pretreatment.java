package data;

import java.io.File;
import java.util.ArrayList;

import po.PBasicInfoPO;
import po.TBasicInfoPO;
import data.readPOs.ReadMBasicPO;
import data.readPOs.ReadPBasicPO;
import data.readPOs.ReadTBasicPO;
import data.savePOs.SaveMBasicPO;
import data.savePOs.SavePAllMatchDataPO;
import data.savePOs.SavePBasicPO;
import data.savePOs.SaveTAllMatchDataPO;
import data.savePOs.SaveTBasicPO;
import dataservice.MatchService;

public class Pretreatment {

	public static void pretreatment(){
		redoTBasic();
		redoPBasic();
		redoMBasic();
	}

	public static void redoPBasic(){
		File f = new File("data\\players\\info");
		File f2 = new File("data\\统计球员基本信息");
		if(!f2.exists())
			SavePBasicPO.savePBasicPO();
		else{
			ArrayList<PBasicInfoPO> pos = ReadPBasicPO.readPBasicPO();
			if(pos.size() == f.list().length)
				SavePBasicPO.savePBasicPO();
		}
	}
	public static void redoTBasic(){
		File f = new File("data\\teams");
		File f2 = new File("data\\统计球队基本信息");
		if(f2.exists()){
			ArrayList<TBasicInfoPO> pos = ReadTBasicPO.readTBasicPO();
			//存在一个总文件
			if(pos.size() == f.list().length - 1)
				SaveTBasicPO.saveTBasicInfo();
		}
		else
			SaveTBasicPO.saveTBasicInfo();
	}	
	
	public static void redoMBasic(){
		MatchService ms = new GetMatchInfo();
		ArrayList<String> seasons = ms.getExistedSeasons();
		for(String season : seasons){
			String path = "data\\matches";
			String path2 = "data\\统计赛季比赛数据" + "\\" + season;
			File f = new File(path);
			File f2 = new File(path2);
			if(f2.exists()){
				if(f.list().length != ReadMBasicPO.readMBasicPO(season).size()){
					SaveMBasicPO.saveAll(season);
					SavePAllMatchDataPO.savePAllMatchDataPO();
					SaveTAllMatchDataPO.saveTAllMatchDataPO();

				}				
			}
			else{
				SaveMBasicPO.saveAll(season);
				SavePAllMatchDataPO.savePAllMatchDataPO();
				SaveTAllMatchDataPO.saveTAllMatchDataPO();
			}
		}
	}
}