package com.erp.vo;

public class searchVO 
{
	private int    pageno;      //������ ��ȣ
	private int    offset;		//������ġ
	private String notice_team; //�μ���
	private String searchKey;   //�˻�(����,����, name, usernum)
	private String searchWord;	//�˻���
	private String team;		//�μ���
	private String level;       //����
	private String user_status; //����
	

	public int    getPageno()      { return pageno;     } 
	public int    getOffset()      { return offset;     }
	public String getNotice_team() { return notice_team;}
	public String getSearchKey()   { return searchKey;  }
	public String getSearchWord()  { return searchWord; }
	public String getTeam()        {return team;	    }
	public String getLevel()       {return level;	    }
	public String getUser_status() {return user_status;	}
	
	

	public void setNotice_team(String notice_team) { this.notice_team = notice_team; }
	public void setSearchKey(String searchKey)     { this.searchKey = searchKey;     }
	public void setSearchWord(String searchWord)   { this.searchWord = searchWord;   }
	public void setOffset(int offset)              {this.offset = offset;	         }
	public void setTeam(String team)               {this.team = team;	             }
	public void setLevel(String level)             {this.level = level;	             }
	public void setUser_status(String user_status) {this.user_status = user_status;	 }
	public void setPageno(int pageno) {
		this.pageno = pageno;
		this.offset = (this.pageno - 1) * 10;
	}
}

