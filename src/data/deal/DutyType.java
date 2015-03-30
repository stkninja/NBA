package data.deal;

public enum DutyType {
	/**时间为0，且数据必为0*/
	TIME_ZERO,
	/**时间为null或None,但数据正确*/
	ONLY_DUTY_TIME,
	/**时间为null或None,但数据全为0*/
	DUTY_TIME_AND_DATA,
	/**得分为null*/
	NULL_POINT,
	/**数据正确*/
	DATA_OK;
}
