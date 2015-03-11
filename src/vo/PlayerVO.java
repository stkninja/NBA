package vo;

import java.awt.Image;

public class PlayerVO {
	public String name;
	public Image portrait;                  //Ф��
	public Image action;                    //������
	public String team;
	public String position;
	public String subArea;                  //����
	public int gameplay;                    //��������
	public int gamestart;                   //�ȷ�����
	public int allrebound;                  //��������
	public double rebound;                  //����������
	public double alloffensiverebound;      //�ܽ���������
	public double offensiverebound;         //��������������
	public double alldefensiverebound;      //�ܷ���������
	public double defensiverebound;         //��������������
	public int allassist;                   //��������
	public double assist;                   //����������
	public double allminute;                //���ڳ�ʱ��
	public double minute;                   //�����ڳ�ʱ��
	public int alloffense;                  //�ܽ�����
    public double offense;                  //����������
	public int alldefence;                  //�ܷ�����
	public double defence;                  //����������
	public int allsteal;                    //��������
	public double steal;                    //����������
	public int allblock;                    //�ܸ�ñ��
	public double block;                    //������ñ��
	public int allerror;                    //��ʧ����
	public double error;                    //����ʧ����
	public int allfoul;                     //�ܷ�����
	public double foul;                     //����������
	public int allpoint;                    //�ܵ÷�
	public double point;                    //�����÷�
	public int allshoot;                    //��Ͷ��������
	public double shoot;                    //����Ͷ��������
	public double allshootmade;             //��Ͷ��������
	public double shootmade;                //����Ͷ��������
	public int allthreepoint;               //�����ֳ�����
	public double threepoint;               //�������ֳ�����
	public double allthreepointmade;        //������������
	public double threepointmade;           //��������������
	public int allfreethrow;                //�ܷ��������
	public double freethrow;                //�������������
	public double allfreethrowmade;         //�ܷ���������
	public double freethrowmade;            //��������������
	public int doubledouble;                //��˫
	public double allfieldgoalpercent;      //��Ͷ��������
	public double fieldgoalpercent;         //����Ͷ��������
	public double allthreepointpercent; //������������
	public double threepointpercent; //��������������
	public double allfreethrowpercent;  //�ܷ���������
	public double freethrowpercent;  //��������������
	public double allefficiency;    //��Ч��
	public double efficiency;    //����Ч��
	public double allgmsc;          //��GmScЧ��ֵ
	public double gmsc;          //����GmScЧ��ֵ
	public double allrealshootpercent;  //����ʵ������
	public double realshootpercent;  //������ʵ������
	public double allshootefficiency;   //��Ͷ��Ч��
	public double shootefficiency;   //����Ͷ��Ч��
	public double allreboundrate;       //��������
	public double reboundrate;       //����������
	public double alloffensivereboundrate;  //�ܽ���������
	public double offensivereboundrate;  //��������������
	public double alldefensivereboundrate;  //�ܷ���������
	public double defensivereboundrate;  //��������������
	public double allassistrate;        //��������
	public double assistrate;        //����������
	public double allstealrate;     //��������
	public double stealrate;     //����������
	public double allblockrate;     //�ܸ�ñ��
	public double blockrate;     //������ñ��
	public double allerrorrate;     //��ʧ����
	public double errorrate;     //����ʧ����
	public double allusage;         //��ʹ����
	public double usage;         //����ʹ����
	
	public PlayerVO(){
		allfieldgoalpercent = allshootmade / allshoot;
		allthreepointpercent = allthreepointmade / allthreepoint;
		allfreethrowpercent = allfreethrowmade / allfreethrow;
		allefficiency = (allpoint + allrebound + allassist + allsteal + allblock) - (allshoot - allshootmade) - (allfreethrow - allfreethrowmade) - allerror;
		allgmsc = allpoint + 0.4 * allshootmade - 0.7 * allshoot - 0.4 * (allfreethrow - allfreethrowmade) + 0.7 * alloffensiverebound + 0.3 * alldefensiverebound + allsteal + 0.7 * allassist + 0.7 * allblock - 0.4 * allfoul - allerror;
		allrealshootpercent = allpoint / (2 * (allshoot + 0.44 * allfreethrow));
		allshootefficiency = (allshootmade + 0.5 * allthreepointmade) / allshoot;
		allerrorrate = allerror / (allshoot - allthreepoint + 0.44 * allfreethrow + allerror);
	}
}

