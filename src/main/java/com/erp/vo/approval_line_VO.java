/*
 * ���� : ���̺� approval_line�� �����͸� ǥ���ϱ� ���� Ŭ����
 * �ۼ��� : 2025.04.11
 * �ۼ��� : ������
 */

package com.erp.vo;

public class approval_line_VO {
	
	private int    approval_no;     //���ڰ��� ��ȣ
	private String approval_target; //������ : ������(usernum)�� user���� �����ð���.
	private String approval_status; //�������
	private String approval_sort;   //�������
	private String approval_date;   //��������
	private String comment;         //�ڸ�Ʈ
	private userVO approver;        //���� ���� ��ü : ������ ����
	
	public int getApproval_no()        {return approval_no;     }
	public String getApproval_target() {return approval_target;	}
	public String getApproval_status() {return approval_status;	}
	public String getApproval_sort()   {return approval_sort;	}
	public String getApproval_date()   {return approval_date;	}
	public String getComment()         {return comment;	        }
	public userVO getApprover()        {return approver;        }
	
	public void setApproval_no(int approval_no)            {this.approval_no     = approval_no;     }
	public void setApproval_target(String approval_target) {this.approval_target = approval_target;	}
	public void setApproval_status(String approval_status) {this.approval_status = approval_status;	}
	public void setApproval_sort(String approval_sort)     {this.approval_sort   = approval_sort;	}
	public void setApproval_date(String approval_date)     {this.approval_date   = approval_date;   }
	public void setComment(String comment)                 {this.comment         = comment;	        }
	public void setApprover(userVO approver)               {this.approver        = approver;        }
	

}
