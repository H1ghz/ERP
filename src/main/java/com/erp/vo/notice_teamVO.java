
package com.erp.vo;

public class notice_teamVO 
{
	private int    notice_no; 
	private String notice_team;
		
	public int    getNotice_no()   {return notice_no;  }
	public String getNotice_team() {return notice_team;}
	public String getTeam_name()
	{
		String name = "";
		switch(notice_team)
		{
		case "100": name = "����";     break;
		case "200": name = "������";   break;
		case "300": name = "�濵����";  break;
		}
		return name;
	}
	
	public void setNotice_no(int notice_no)        {this.notice_no   = notice_no;  }
	public void setNotice_team(String notice_team) {this.notice_team = notice_team;}  
}
