/*
 * ���� : ���̺� approval_file�� �����͸� ǥ���ϱ� ���� Ŭ����
 * �ۼ��� : 2025.04.11
 * �ۼ��� : ������
 */
package com.erp.vo;

public class approval_file_VO {
	
	private int approval_no;    //���ڰ��� ��ȣ
	private String apname;      //÷������ ������
	private String afname;      //÷������ ����
	
	
	public int getApproval_no()    {return approval_no; }
	public String getApname()      {return apname;	    }
	public String getAfname()      {return afname;	    }
	
	
	public void setApproval_no(int approval_no)    {this.approval_no = approval_no;}
	public void setApname(String apname)           {this.apname      = apname;	   }
	public void setAfname(String afname)           {this.afname      = afname;	   }
	
	
}
