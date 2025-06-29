/*
 * ���� : ���̺� approval�� �����͸� ǥ���ϱ� ���� Ŭ����
 * �ۼ��� : 2025.04.11
 * �ۼ��� : ������
 */
package com.erp.vo;

public class approvalVO {
	
	private int    approval_no;      //���ڰ��� ��ȣ
	private String kind;             //��������
	private String writedate;        //�ۼ�����
	private String approval_title;   //���ڰ��� ����
	private String approval_content; //���ڰ��� ����
	private String document_status;  //�������� ����
	private String usernum;          //�����ȣ : �ۼ���(usernum)�� user���� FOREIGN KEY�� �����ð���.
	private String approval_code;    //ǰ�ǹ�ȣ
	private userVO drafter;			 //���� ���� ��ü : �ۼ��� ����
	
	public int getApproval_no()         {return approval_no;        }
	public String getKind()             {return kind;               }
	public String getWritedate()        {return writedate;	        }
	public String getApproval_title()   {return approval_title;	    }
	public String getApproval_content() {return approval_content;   }
	public String getDocument_status()  {return document_status;	}
	public String getUsernum()          {return usernum;	        }
	public String getApproval_code()    {return approval_code;	    }
	public userVO getDrafter()          {return drafter; 			}
	
	
	
	public void setApproval_no(int approval_no)              {this.approval_no      = approval_no;      }
	public void setKind(String kind)                         {this.kind             = kind;	            }
	public void setWritedate(String writedate)               {this.writedate        = writedate;        }
	public void setApproval_title(String approval_title)     {this.approval_title   = approval_title;   }
	public void setApproval_content(String approval_content) {this.approval_content = approval_content;	}
	public void setDocument_status(String document_status)   {this.document_status  = document_status;	}
	public void setUsernum(String usernum)                   {this.usernum 			= usernum;	        }
	public void setApproval_code(String approval_code)       {this.approval_code 	= approval_code;	}
	public void setDrafter(userVO drafter)                   {this.drafter          = drafter;          }
	
	
}
